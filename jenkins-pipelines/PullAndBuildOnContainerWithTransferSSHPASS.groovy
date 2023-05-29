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


        stage('Transfer to Tomcat Host') {
    steps {
        script {
            def tomcatHost = '192.168.56.111'
            def tomcatUsername = 'dockeradmin'
            def tomcatPassword = 'dockeradmin'
            def remoteDirectory = '/opt/docker'
            def remoteFilePath = remoteDirectory + '/webapp.war'

            sh "sshpass -p '${tomcatPassword}' scp -o StrictHostKeyChecking=no -r /var/jenkins_home/workspace/BuildDeployContainerPipelines/webapp/target/webapp.war ${tomcatUsername}@${tomcatHost}:${remoteFilePath}"
        }
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
                                execCommand: 'cd /opt/docker ; docker build -t regapp:v2 .; docker stop registerappv2; docker rm registerappv2;  docker run -d --name registerappv2 -p 8088:8080 regapp:v1',
                                execTimeout: 300000,
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
