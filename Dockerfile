FROM maven:3.9.7-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/api-0.0.1-SNAPSHOT.war /app/app.war

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=default", "/app/app.war"]