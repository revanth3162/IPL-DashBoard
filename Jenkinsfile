def projectName = 'ipldashboard'
def version = "0.0.${currentBuild.number}"

def appName = "${projectName}-app"


def dockerAppImageTag = "${projectName}-app:${version}"


pipeline {
    agent any

    stages {

        // If you want to build your JAR file as part of the Jenkins build:
        
        stage('Package Spring Boot application into a JAR via Maven') {
            steps {
                sh 'chmod a+x mvnw'
                sh "./mvnw package"
            }
        }
        

        stage('Build docker images') {
            steps {
                sh "docker build -f Dockerfile -t ${dockerAppImageTag} /var/lib/jenkins/workspace/iplboard"
                sh "readlink -f ipl-dashboard-0.0.1-SNAPSHOT.jar"
            }
        }

        stage('Deploy images To OpenShift') {
            steps {
                sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"

                sh "oc project ${projectName} || oc new-project ${projectName}"

                sh "oc delete all -l app=${appName} || echo 'Unable to delete all previous OpenShift app resources'"
              

                sh "oc new-app ${appName}:${version}  -l version=${version}"
               

                sh "oc expose svc/${appName}"
            }
        }
    }
}