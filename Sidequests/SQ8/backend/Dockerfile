# Stage 1: Build the JAR file
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory for the build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/todo-0.0.1-SNAPSHOT.jar /app/todo.jar

# Define the command to run the JAR file with arguments
CMD ["java", "-jar", "todo.jar"]