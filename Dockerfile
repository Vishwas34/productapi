# Use an official OpenJDK runtime as a parent image
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY ./target/productapi-0.0.1-SNAPSHOT.jar productapi.jar

# Expose the port the application runs on
EXPOSE 8081

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "/app/productapi.jar"]
