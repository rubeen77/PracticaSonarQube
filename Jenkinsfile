pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rubeen77/PracticaSonarQube.git'
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

        stage('Package') {
            steps {
                sh 'cd BibliotecaApp && mvn package -DskipTests'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'BibliotecaApp/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado correctamente'
        }
        failure {
            echo 'Error en el pipeline'
        }
    }
}
