pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                sh 'rm -rf BibliotecaApp'
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rubeen77/PracticaSonarQube.git'
            }
        }

        stage('Build') {
            steps {
                sh 'cd BibliotecaApp && mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'cd BibliotecaApp && mvn test'
            }
        }
        stage('Package'){
            steps {
                sh 'cd BibliotecaApp && mvn package'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'BibliotecaApp/target/*.jar'
            }
        }
    }
    post { 
        success {
            echo 'Pipeline ejecutado correctamente'
        }
        failure {
            echo 'Hubo un error en el pipeline'
        }
    }
}
