FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file into the container
COPY src/webflux-moldunity-0.0.1-SNAPSHOT.jar /app/app.jar

# Run the app
#ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["java", "-jar", "app.jar"]