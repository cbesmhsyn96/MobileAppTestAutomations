pipeline {
    agent any

    tools {
        maven 'Maven 3.9.3'
        jdk 'JDK22'
        allure 'Allure'
    }

    environment {
        ANDROID_HOME = '/Users/huseyinakcan/Library/Android/sdk'
        PATH = "${env.ANDROID_HOME}/platform-tools:${env.ANDROID_HOME}/emulator:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
        APPIUM_PORT = '4723'
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
                    nohup \$ANDROID_HOME/emulator/emulator -avd Pixel_4 -no-window -no-audio > emulator.log 2>&1 &
                    EMU_PID=\$!

                    adb wait-for-device
                    echo "Waiting for emulator to fully boot..."
                    boot_completed=""
                    until [ "\$boot_completed" = "1" ]; do
                        boot_completed=\$(adb shell getprop sys.boot_completed 2>/dev/null || echo 0)
                        sleep 5
                    done
                    echo "Emulator fully booted!"
                """
            }
        }

        // Appium zaten manuel çalıştırılacak, pipeline'da başlatma kısmı kaldırıldı
        stage('Verify Appium') {
            steps {
                sh """
                    echo "Checking if Appium server is running on port ${APPIUM_PORT}..."
                    if nc -z localhost ${APPIUM_PORT}; then
                        echo "Appium server is up!"
                    else
                        echo "Appium server is NOT running. Please start it manually!"
                        exit 1
                    fi
                """
            }
        }

        stage('Build & Test') {
            steps {
                dir('AndroidProjects/EnuygunAppTest') {
                    sh """
                        mvn clean test
                    """
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
