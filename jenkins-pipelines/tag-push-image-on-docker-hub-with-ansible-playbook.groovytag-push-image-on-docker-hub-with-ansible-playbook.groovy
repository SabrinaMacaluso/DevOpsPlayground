pipeline {
    agent any

 triggers {
        pollSCM '* * * * *'
    }
    
    stages {
        stage('Build') {
            steps {
                 git 'https://github.com/SabrinaMacaluso/mywebapp'
            }

            
        }
        
    }
    
      
      post {
        always {
            sshPublisher(publishers: [
                sshPublisherDesc(configName: 'ansible', transfers: [
                    sshTransfer(cleanRemote: false, execCommand: 'ansible-playbook /opt/docker/myapp.yml && sleep 10 && ansible-playbook /opt/docker/deploy-regapp.yml', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/opt/docker', remoteDirectorySDF: false, removePrefix: 'webapp/target', sourceFiles: 'webapp/target/webapp.war')
                ])
            ])
        }
    }
}