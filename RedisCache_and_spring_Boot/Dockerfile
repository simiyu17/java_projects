FROM openjdk:11.0.7-jre-slim
WORKDIR /opt
COPY target/sampleRedisCache.jar /opt/sampleRedisCache.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sampleRedisCache.jar"]