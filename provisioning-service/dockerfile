FROM maven:4.0.0-rc-5-eclipse-temurin-21 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean install -DskipTests
FROM eclipse-temurin:21-jdk
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
CMD ["java","-jar","app.jar"]