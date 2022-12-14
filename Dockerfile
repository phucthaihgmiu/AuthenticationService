FROM eclipse-temurin:17-alpine as builder
WORKDIR extracted
ADD ./target/AuthenticationService-0.0.1-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17-alpine
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

#FROM openjdk:17-ea-jdk
#
#COPY ./target/AuthenticationService-0.0.1-SNAPSHOT.jar /app.jar
#
#EXPOSE 5501
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]
