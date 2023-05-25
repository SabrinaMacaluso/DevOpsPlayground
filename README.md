# DevOpsPlayground

## Plugin to install in Jenkins

- GitHub plugin
- Maven Integration plugin
- Deploy to container 
- Publish Over SSH
- SSH Pipeline Steps


## Jenkins Commands
```docker build -t myjenkins-image .```

```docker run -d --name myjenkins-server -p 8080:8080 myjenkins-image```

## Tomcat Commands

```docker build -t mytomcatimage .```

```docker run -d --name mytomcat-server -p 8081:8080 mytomcatimage```

```docker exec -it mytomcat-server /bin/bash```

```docker stop mytomcat-server```
```docker rm mytomcat-server```

```docker run -d --name mytomcat-server -v /root/tomcat/my-webapp:/opt/tomcat/webapps/ROOT -p 8081:8080 tomcatimage```
