pipeline {

  agent {
    label 'Slave_Induccion'
  }

  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
 	 disableConcurrentBuilds()
  }

  tools {
    jdk 'JDK8_Centos'
    gradle 'Gradle6.0.1_Centos'
  }

  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout([
			$class: 'GitSCM',
			branches: [[name: '*/main']],
			doGenerateSubmoduleConfigurations: false,
			extensions: [],
			gitTool: 'Default',
			submoduleCfg: [],
			userRemoteConfigs: [[
				credentialsId: 'GitHub_Richardace',
				url:'https://github.com/Richardace/adn-veterinary'
			]]
		])
      }
    }

    stage('Clean') {
      steps{
        echo "------------>Clean<------------"
	dir("veterinary") {
            sh 'gradle --b ./build.gradle clean'
	}
      }
    }

    stage('Unit Tests') {
      steps{
        echo "------------>Unit Tests<------------"
	dir("veterinary") {
            sh 'gradle --b ./build.gradle clean'
            sh 'gradle --b ./build.gradle jacocoTestReport'
	}
      }
    }

    stage('Static Code Analysis') {
      steps{
          echo '------------>Análisis de código estático<------------'

		withSonarQubeEnv('Sonar') {
                  sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }


       }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
	dir("veterinary") {
	    sh 'gradle --b ./build.gradle build -x test'
	}

      }
    }
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
    }
    failure {
      echo 'This will run only if failed'
    }
  }
}