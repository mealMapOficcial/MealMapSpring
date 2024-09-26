# Build stage
FROM maven:3-eclipse-temurin-17 AS build

# Establece el directorio de trabajo
WORKDIR /usr/src/app

# Copia el pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Empaqueta la aplicación sin correr los tests
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /usr/src/app

# Copia el .jar generado en la etapa anterior
COPY --from=build /usr/src/app/target/MealMap-0.0.1-SNAPSHOT.jar template-docker.jar

# Exponemos el puerto donde correrá el servicio de Spring Boot
EXPOSE 8080

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "template-docker.jar"]