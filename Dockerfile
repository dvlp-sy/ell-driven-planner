FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/planner-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.yaml ./
ENTRYPOINT ["java", "-jar", "app.jar"]