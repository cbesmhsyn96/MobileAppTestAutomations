pipeline {
    agent any

    tools {
        // git 'Git 2.46.0'  // Kaldırıldı, Jenkins PATH üzerinden default git kullanacak
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
                    export ANDROID_HOME=${ANDROID_HOME}
                    export PATH=\$ANDROID_HOME/platform-tools:\$ANDROID_HOME/emulator:/opt/homebrew/bin:\$PATH

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

        stage('Start Appium') {
            steps {
                script {
                    try {
                        sh """
                            export PATH=\$ANDROID_HOME/platform-tools:\$ANDROID_HOME/emulator:/opt/homebrew/bin:\$PATH

                            APPIUM_EXEC=/opt/homebrew/bin/appium

                            # Eğer port meşgulse kapat
                            pids=\$(lsof -ti :${APPIUM_PORT})
                            if [ -n "\$pids" ]; then
                                echo "Port ${APPIUM_PORT} already in use. Killing: \$pids"
                                kill -9 \$pids
                            fi

                            echo "Starting Appium server..."
                            nohup \$APPIUM_EXEC --session-override --port ${APPIUM_PORT} > appium.log 2>&1 &
                            APPIUM_PID=\$!
                            echo "Appium PID: \$APPIUM_PID"

                            # Appium server açılması için bekle
                            for i in {1..15}; do
                                nc -z localhost ${APPIUM_PORT} && break
                                sleep 1
                            done
                            echo "Appium server should be ready."
                        """
                    } catch (err) {
                        echo "Appium server start failed, continuing pipeline: ${err}"
                    }
                }
            }
        }

        stage('Build & Test') {
            steps {
                dir('AndroidProjects/EnuygunAppTest') {
                    sh """
                        export ANDROID_HOME=${ANDROID_HOME}
                        export PATH=\$ANDROID_HOME/platform-tools:\$ANDROID_HOME/emulator:/opt/homebrew/bin:\$PATH

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
