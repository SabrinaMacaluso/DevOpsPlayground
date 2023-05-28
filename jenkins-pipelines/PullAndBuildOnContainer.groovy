pipeline {
    agent any
    
    triggers {
        pollSCM '* * * * *'
    }
   
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/SabrinaMacaluso/mywebapp'
            }
        }
        stage('Build') {
            steps {
                sh '/opt/maven/bin/mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'tomcat',
                        transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'cd /opt/docker ; docker build -t regapp:v1 .; docker stop registerapp; docker rm registerapp;  docker run -d --name registerapp -p 8088:8080 regapp:v1',
                                execTimeout: 120000,
                                flatten: false,
                                makeEmptyDirs: false,
                                noDefaultExcludes: false,
                                patternSeparator: '[, ]+',
                                remoteDirectory: '//opt//docker',
                                remoteDirectorySDF: false,
                                removePrefix: 'webapps/target',
                                sourceFiles: 'webapps/target/webapp.war',
                                useWorkspaceInPromotion: false,
                                verbose: false
                            )
                        ]
                    )
                ])
            }
        }
    }
}