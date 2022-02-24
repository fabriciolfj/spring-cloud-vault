
FROM openjdk:11.0.7-jdk

LABEL maintainer = "fabricio.jacob@outlook.com"

ADD /target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]