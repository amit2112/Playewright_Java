pipeline {

    agent { 
    	docker { 
    		image 'mcr.microsoft.com/playwright/java:v1.38.0-jammy' 
    		} 
    	}
    	
    tools{
    	maven 'maven'
        }
    
    stages {
		      stage('Playwright Setup') {
		         steps {
		            sh 'mvn -B install -D skipTests --no-transfer-progress dependency:go-offline'
		            	}
		           	}     
		        
		        stage('Deploy to QA') {
		            steps {
		                echo 'Deploy to QA'
			            }
			        }
		        
		        stage('Regression Automation Test') {
		            steps{
		            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
		                git 'https://github.com/amit2112/Playewright_Java.git'
		                sh 'mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --with-deps chromium"'
		                sh 'xvfb-run mvn clean test dependency:go-offline -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml'
			            }
			        }
			     }
		        
		        stage('Publish Extent Report'){
		            steps{
		                publishHTML([allowMissing:false, 
		                alwaysLinkToLastBuild:false,
		                keepAll:true,
		                reportDir:'build',
		                reportFiles: 'TestExecutionReport.html',
		                reportName:'HTML Extent Report',
		                reportTitles:''])}
		        	}
    	}
}
