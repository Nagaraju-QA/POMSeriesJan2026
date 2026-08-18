pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                echo "build the project"
            }
        }

        stage("Run Unit Test") {
            steps {
                echo "run UTs"
            }
        }

        stage("Run Integration Test") {
            steps {
                echo "run ITs"
            }
        }

        stage("Deploy to Dev") {
            steps {
                echo "deploy to dev"
            }
        }

         stage("Deploy to QA") {
            steps {
                echo "Deploy to QA"
            }
        }

        stage("Run regression test cases on QA") {
            steps {
                echo "Run test cases on QA"
            }
        }

        stage("Deploy to Stage") {
            steps {
                echo "Deploy to Stage"
            }
        }

        stage("Run sanity test cases on Stage") {
            steps {
                echo "Run sanity test cases on Stage"
            }
        }

        stage("Deploy to PROD") {
            steps {
                echo "Deploy to PROD"
            }
        }
    }
}