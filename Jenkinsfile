pipeline {
    agent any

    tools {
        maven 'Maven 3.9.3'   // Global Tool Configuration’daki isimle birebir aynı
        jdk 'JDK22'           // Global Tool Configuration’daki isimle birebir aynı
    }

    environment {
        ANDROID_HOME = '/Users/huseyinakcan/Library/Android/sdk'  // Android SDK yolu
        PATH = "${env.ANDROID_HOME}/platform-tools:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/cbesmhsyn96/MobileAppTestAutomations.git'
            }
        }

        stage('Build & Test') {
            steps {
                dir('AndroidProjects/EnuygunAppTest') {
                    sh 'mvn clean test'
                }
            }
        }

        stage('Allure Report') {
            steps {
                dir('AndroidProjects/EnuygunAppTest') {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'AndroidProjects/EnuygunAppTest/target/allure-report/**', allowEmptyArchive: true
        }
    }
}
