pipeline {
    agent any

    tools {
        maven 'jenkins-maven'
        dockerTool 'jenkins-docker'
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/RajshreeUmarekar/news-app-spring-boot-v2']])
                bat 'mvn clean install'
            }
        }
        // stage('Build Docker Image') {
        //     steps {
        //         script {
        //             bat 'docker build -t rajshreeu/news-app-jenkins-docker-backend .'
        //         }
        //     }
        // }
        stage('Push Image to Hub'){
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerHubPwd', variable: 'dockerHubPwd')]) {
                        bat 'docker login -u rajshreeu -p ${dockerHubPwd}'
                    }
                    bat 'docker push rajshreeu/news-app-jenkins-docker-backend'
                }
            }
        }
    }
}
