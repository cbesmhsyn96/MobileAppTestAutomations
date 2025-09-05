pipeline {
    agent any

    tools {
        maven 'Maven 3.9.3'
        jdk 'JDK22'
        allure 'Allure'
    }

    environment {
        ANDROID_HOME = '/Users/huseyinakcan/Library/Android/sdk'
        PATH = "/usr/bin:/bin:/usr/sbin:/sbin:${env.ANDROID_HOME}/platform-tools:${env.ANDROID_HOME}/emulator:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/cbesmhsyn96/MobileAppTestAutomations.git'
            }
        }

        stage('Start Emulator') {
            steps {
                sh """
                  echo "Starting Android Emulator..."
                  $ANDROID_HOME/emulator/emulator -avd Pixel_4 -no-window -no-audio &
                  adb wait-for-device
                  adb devices
                  echo "Waiting for emulator to fully boot..."
                  sleep 30
                """
            }
        }

        stage('Start Appium') {
            steps {
                sh '''
                adb wait-for-device
                adb shell getprop sys.boot_completed | grep 1
                '''
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
                        results: [[path: 'target/allure-results']],
                        reportBuildPolicy: 'ALWAYS'
                    ])
                }
            }
        }
    }
}
