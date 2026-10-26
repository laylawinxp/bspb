pipeline {
    agent any

    options {
        timeout(time: 10, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '7'))
    }
    
    environment {
        USE_SELENOID = 'true'
        SELENOID_IP = '192.168.0.102'
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

                sh "./gradlew clean test --no-daemon -DuseSelenoid=${USE_SELENOID} -DselenoidUrl=http://${SELENOID_IP}:4444/wd/hub"
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            archiveArtifacts artifacts: 'build/reports/tests/test/**', fingerprint: true, allowEmptyArchive: true
        }

        failure {
            echo 'Сборка остановлена: some tests failed'
        }
    }
}
