pipeline {
    agent any

    options {
        timeout(time: 10, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '7'))
    }

    tools {
        gradle 'gradle'
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

                sh './gradlew clean test --no-daemon -DuseSelenoid=${USE_SELENOID}'
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