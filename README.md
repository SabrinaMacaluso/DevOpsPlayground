# DevOpsPlayground

![readmee](https://github.com/SabrinaMacaluso/DevOpsPlayground/assets/104983001/1e2a5dac-e15e-42ed-8261-be7ad1d5fad8)

## Plugin to install in Jenkins

- GitHub plugin
- Maven Integration plugin
- Deploy to container 
- Publish Over SSH
- SSH Pipeline Steps



## Setting up Jenkins and Tomcat with Docker

### Jenkins Commands

Build Docker image for Jenkins: 

```bash
docker build -t myjenkins-image .
```
Create the Jenkins container:

```bash
docker run -d --name myjenkins-server -p 8080:8080 myjenkins-image
```
Create the Jenkins container with volume using docker-compose in the `docker-compose/jenkins` directory:

```bash
docker-compose up 
```

### Tomcat Commands

Build Docker image for Tomcat: 

```bash
docker build -t mytomcatimage .
```

Create the Tomcat container:
```bash
docker run -d --name mytomcat-server -p 8081:8080 mytomcatimage
```

Access the Tomcat server's shell:

```bash
docker exec -it mytomcat-server /bin/bash
```
Stop the Tomcat container
```bash
docker stop mytomcat-server
```
Remove the Tomcat container
```bash
docker rm mytomcat-server
```
Run the Tomcat container with a volume
```bash
docker run -d --name mytomcat-server -v /root/tomcat/my-webapp:/opt/tomcat/webapps/ROOT -p 8081:8080 tomcatimage
```




## Other Commands

### Check volumes

```bash
docker volume ls
```

### remove all containers

```bash
docker rm $( docker ps -aq )
```

### remove image 

```bash
docker rmi <image-id>
```

### Push images in dockerhub

```bash
docker login
```

```bash
docker tag myjenkinsfordockerrepo:latest sabrinasabrina/myjenkinsimage
```

```bash
docker push sabrinasabrina/myjenkinsimage
```

### Locate java opendjk installation directory

```bash
readlink -f $(which java)
```

## Setting up Jenkins server

Install Maven on the Jenkins server:

```bash
wget https://dlcdn.apache.org/maven/maven-3/3.9.2/binaries/apache-maven-3.9.2-bin.tar.gz
tar -xzvf apache-maven-3.9.2-bin.tar.gz
```

Update the .profile to configure the variables for maven on the jenkins server:

```bash
M2_HOME=/opt/maven
M2=/opt/maven/bin
#To locate openjdk, use readlink -f $(which java)
JAVA_HOME=/opt/java/openjdk
PATH=$PATH:$HOME/bin:$JAVA_HOME:$M2_HOME:$M2
export PATH
```
Apply the changes with:
```bash
source .profile
```

## SSH Configuration
Install the openssh-server and configure the ssh_config file:
```bash
apt install openssh-server
```

```bash
nano /etc/ssh/ssh_config 
```

In the ssh_config file, uncheck PasswordAuthentication and Port 22:

 ```bash
 PasswordAuthentication yes
 Port 22
```

Restart the SSH service:
 ```bash
service ssh restart
```
 ## Minikube
 Apply the ConfigMap with your credentials:
  ```bash
 kubectl apply -f dockerhub-credentials.yaml
```  
 Apply the Deployment: 
 
  ```bash
 kubectl apply -f myapphub-deployment.yaml
 ```
 Apply the Service:
  ```bash
 kubectl apply -f myapphub-service.yaml
 ```
 
 ## Ansible
 
To encode your password using the base64 command:
```bash
 echo -n "yourlogin" | base64
 ```
 
 To apply the playbook for creating the Docker image and pushing it to DockerHub, run the command:
```bash 
 ansible-playbook create_image.yaml
  ```
 To apply the playbook for deploying the web application pulled from DockerHub to Minikube, run the command:
 ```bash
 ansible-playbook minikube-deploy-monapp.yaml
 ```
 
 To access the web application deployed in Minikube
  ```bash
 minikube service myapphub-service --url
```
 
  ## Proxy configuration
  
  ### Maven
  
  Modify the setting.xml file in Maven and add the proxy configuration.
  
![maven-setting-xml-config-proxy](https://user-images.githubusercontent.com/104983001/236400088-a5c10f69-2704-4180-89a9-ba7b7777c6b8.png)
  


Docker
Modify the /usr/lib/systemd/system/docker.service file using the following command:

```bash
nano /usr/lib/systemd/system/docker.service

```

```bash
Environment=http_proxy=http://<IP-PROXY>:<PORT>/
Environment=no_proxy=localhost,127.0.0.1
Environment=https_proxy=http://<IP-PROXY>:<PORT>/
```