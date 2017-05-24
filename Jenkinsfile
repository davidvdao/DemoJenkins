
pipeline {
    agent any
    
    stages {
        stage('build') {
            steps {
                parallel (
                    "AWS VM" : {
                        node('window') {
                            bat 'echo window'    
                        }
                    },
                    "Physical machine" : {
                        node('laptop') {
                            bat 'echo laptop'    
                        }
                    }
                )                
            }
        }
    }
}
