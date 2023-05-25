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
