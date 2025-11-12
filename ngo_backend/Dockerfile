# Stage 1: Build the Java application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src /app/src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/ngo_backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the server port
EXPOSE 9090

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
