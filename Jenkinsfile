pipeline {
    agent { docker { image 'maven_3_5_3' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}