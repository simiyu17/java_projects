FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","app.jar"]