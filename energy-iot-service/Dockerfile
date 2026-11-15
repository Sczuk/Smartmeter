FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -ntp -e clean package -DskipTests
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "mkdir -p /app/certs && printf '%s\\n' \"$MQTT_CA_CERT\" > /app/certs/ca.crt && exec java -jar app.jar"]