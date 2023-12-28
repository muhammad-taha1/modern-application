FROM openjdk:17-jdk-slim
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java -jar -Dspring.profiles.active=prod app.jar

