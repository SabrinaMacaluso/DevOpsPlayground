# DevOpsPlayground

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
