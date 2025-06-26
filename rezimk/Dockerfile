# Stage 1: build the JAR
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy only what’s needed for dependency resolution
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: run the JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]