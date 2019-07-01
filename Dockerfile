FROM maven:3.6.1-jdk-8-slim

COPY . /usr/app/
WORKDIR /usr/app/
RUN mvn package

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/rest-api-test-1.0-SNAPSHOT.jar"]