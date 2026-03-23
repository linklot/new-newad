# --- Build Stage ---
# Use a Gradle image that supports Java 25 (e.g., a recent one)
FROM gradle:9.1.0-jdk25 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle settings.gradle ./

# Copy the source code
COPY src ./src

# Run the Gradle bootJar task to build the executable JAR
RUN gradle bootJar --no-daemon

# --- Run Stage ---
FROM openjdk:25-ea-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]