# // TODO: Implementar el Dockerfile
FROM maven:3.8.4-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN mkdir -p /data

ENTRYPOINT ["java", "-jar", "app.jar"]