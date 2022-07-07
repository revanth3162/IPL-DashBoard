FROM openjdk:11.0
COPY /target/ipl-dashbaord-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]