FROM maven:3-eclipse-temurin-17 AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /target/template-0.0.1.jar template-docker.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "template-docker.jar"]