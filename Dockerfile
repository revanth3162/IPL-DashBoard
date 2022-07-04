
FROM openjdk:16-alpine3.13

COPY target/ipl-dashboard-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]

