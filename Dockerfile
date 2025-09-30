FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/newkingsite-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8086

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]