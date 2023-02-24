FROM openjdk:8-jdk-alpine

COPY target/ProjectCache-0.0.1-SNAPSHOT.jar ProjectCache-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ProjectCache-0.0.1-SNAPSHOT.jar"]