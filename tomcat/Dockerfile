FROM ubuntu:20.04

RUN apt-get update && \
    apt-get install -y openjdk-11-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir /opt/tomcat
WORKDIR /opt/tomcat

ADD https://downloads.apache.org/tomcat/tomcat-9/v9.0.74/bin/apache-tomcat-9.0.74.tar.gz .

RUN tar -xvzf apache-tomcat-9.0.74.tar.gz
RUN mv apache-tomcat-9.0.74/* /opt/tomcat

#COPY index.jsp /opt/tomcat/webapps/ROOT/
EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]