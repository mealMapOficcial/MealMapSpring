FROM maven:3-eclipse-temurin-17 AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /target/MealMap-0.0.1.jar meal-map.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "meal-map.jar"]