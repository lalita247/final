


FROM tomcat:8.5.63-jdk8-openjdk-slim-buster

ENV CATALINA_OUT="/dev/stdout"

COPY target/*.jar /usr/local/tomcat/webapps/ROOT.jar

ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh", "run"]