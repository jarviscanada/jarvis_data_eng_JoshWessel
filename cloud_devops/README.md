Table of Contents
* [Introduction](#Introduction)
* [Application Architecture](#Application-Architecture)
* [Jenkins CI/CD Pipeline](#Jenkins-CI/CD-Pipeline)
* [Improvements](#Improvements)

# Introduction

The Cloud/DevOps project involves deploying an existing trading application to Azure cloud services.
Deploying the application used an Azure Kubernetes Service containing a master node and a worker node.
The master node uses an API server for connecting with the Kubernetes API.
The worker node contains a load balancer that distributes traffic to the pods. It also uses a pod to store the PSQL database.
The project performed the deployment using two different deployment environments. A dev deployment preceded the final prod deployment.
The difference between the two deployments is that the dev deployment used a demo Docker image, whereas the prod deployment used the proper Docker image.
The project also uses a Jenkins CI/CD pipeline to automate deployment.
A Jenkinsfile defines the three stages in the pipeline: Init, Build, and Deploy.
The project used Azure cloud services to provision the cluster and all other services related to the project.

# Application Architecture

The project's architecture uses an Azure Kubernetes Service that includes a master node and a single worker node.
The master node contains the API server, scheduler, and controller manager.
The kubectl command controls the master node.
The worker node contains a load balancer that distributes traffic to a pair of trading-app pods.
The pods connect to a third pod that stores the PSQL database.
The three pods connect to a kubelet that facilitates communication with the master node.

![K8s Architecture](assets/K8s%20Architecture.png)

# Jenkins CI/CD Pipeline

A Jenkinsfile defines the Jenkins CI/CD pipeline.
It contains the environment variables needed for the pipeline to execute.
It also contains the three stages in the pipeline: Init, Build, and Deploy.
The Init stage contains a single command: the `az login --service-principal` command for logging in to Azure using a service principal.
The Build stage also contains a single command: the `az acr build` command, which builds the image from a Dockerfile.
Finally, the Deploy stage contains several commands:
- First, it runs `az aks get-credentials` to login to AKS.
- Then, it runs `kubectl apply` to create the deployment and pods.
- After that, it runs `kubectl set image` to update the image.
- Finally, `kubectl delete` is run to clean up the deployment and pods.

After each command in the Deploy stage, the `kubectl get all` command checks the deployment's status.
`kubectl cluster-info` is also run after logging into AKS to check the status of the cluster.

![Jenkins CI/CD Pipeline](assets/Jenkins%20CI_CD%20Pipeline%20Diagram.png)

# Improvements
Below are a few improvements to consider:
1. Perform more thorough testing
2. Add comments to the Jenkinsfile
3. Explore auto-scaling options