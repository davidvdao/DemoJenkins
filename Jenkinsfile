pipeline {
  agent {
    node {
      label 'win'
    }
    
  }
  stages {
    stage('build') {
      steps {
        sh 'mvn --version'
      }
    }
  }
}