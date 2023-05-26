FROM ubuntu:20.04
RUN apt-get update && \
    apt-get install -y default-jdk
ADD https://get.jenkins.io/war-stable/2.303.2/jenkins.war /usr/share/jenkins/jenkins.war
EXPOSE 8080
CMD ["java", "-jar", "/usr/share/jenkins/jenkins.war"]