pipeline {
    agent any

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '7'))
    }
    
    environment {
        USE_SELENOID = 'true'
        SELENOID_URL = 'http://192.168.0.102:4444/wd/hub'
    }
    
    tools {
        jdk 'jdk21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh 'chmod +x gradlew'

                sh './gradlew clean test --no-daemon'
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            archiveArtifacts artifacts: 'build/reports/tests/test/**', fingerprint: true, allowEmptyArchive: true

            emailext(
                to: 'laylawinxp@mail.ru',
                subject: "Jenkins Build: ${currentBuild.fullDisplayName} - ${currentBuild.currentResult}",
                body: """
                    Статус сборки: ${currentBuild.currentResult}
                    Проект: ${env.JOB_NAME}
                    Номер сборки: ${env.BUILD_NUMBER}
                    Ссылка: ${env.BUILD_URL}
                """,
                attachLog: true
            )
                
        }

        failure {
            echo 'Сборка остановлена с ошибкой'
        }
    }
}
