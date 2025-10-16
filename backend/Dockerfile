FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Cache dependencies separately
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src src

# Build application
RUN ./mvnw clean package -DskipTests

# Final stage - create a lightweight runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/webflux-moldunity-0.0.1-SNAPSHOT.jar app.jar

# Set the command to run your application
CMD ["java", "-jar", "app.jar"]















