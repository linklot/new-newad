FROM openjdk:25-ea-jdk-slim
WORKDIR /app
COPY build/libs/*-SNAPSHOT.jar web.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/web.jar"]