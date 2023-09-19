pipeline {
    agent any
    
    tools{
        maven 'maven'
    }

    stages {
        
        stage('Build'){
            steps{
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
            
            post{
                success{
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage('Deploy to QA') {
            steps {
                echo 'Deploy to QA'
            }
        }
        
        stage('Regression Automation Test'){
            steps{
                git 'https://github.com/amit2112/Playewright_Java'
                sh 'mvn clean -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
        
        stage('Publish Extent Report'){
            steps{
                publishHTML([allowMissing:false, 
                alwaysLinkToLastBuild:false,
                keppAll:true,
                reportDirectory:'build',
                reportFiles: 'TestExecutionReport.html',
                reportName:'HTML Extent Report',
                reportTitles:''])
            }
        }
    }
}