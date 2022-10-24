FROM openjdk:17-ea-jdk

COPY ./target/AuthenticationService-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 5501

ENTRYPOINT ["java", "-jar", "/app.jar"]