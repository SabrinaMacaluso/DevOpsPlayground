# DevOpsPlayground

## Plugin to install in Jenkins

- GitHub plugin
- Maven Integration plugin
- Deploy to container 
- Publish Over SSH
- SSH Pipeline Steps


## Jenkins Commands
```bash
docker build -t myjenkins-image .
```

```bash
docker run -d --name myjenkins-server -p 8080:8080 myjenkins-image
```

## Tomcat Commands

```bash
docker build -t mytomcatimage .
```

```bash
docker run -d --name mytomcat-server -p 8081:8080 mytomcatimage
```

```bash
docker exec -it mytomcat-server /bin/bash
```

```bash
docker stop mytomcat-server
```

```bash
docker rm mytomcat-server
```

```bash
docker run -d --name mytomcat-server -v /root/tomcat/my-webapp:/opt/tomcat/webapps/ROOT -p 8081:8080 tomcatimage
```




## Other Commands

### remove all containers

To remove all containers, use the following command: ```docker rm $( docker ps -aq )```

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
