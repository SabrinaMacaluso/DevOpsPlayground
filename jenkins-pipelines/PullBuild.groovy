pipeline {
    agent any

    stages {
        stage('Clone repository') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/SabrinaMacaluso/mywebapp'
            }
        }
        stage('Build with Maven') {
            steps {
                // Build with Maven 
                sh '/opt/maven/bin/mvn clean package'
            }    
        }
    }
}