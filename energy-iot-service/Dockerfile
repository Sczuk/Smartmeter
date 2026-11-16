FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY smartmeter-security-commons/pom.xml ./smartmeter-security-commons/pom.xml
COPY smartmeter-security-commons/src ./smartmeter-security-commons/src
RUN mvn -B -ntp -f smartmeter-security-commons/pom.xml clean install -DskipTests
COPY energy-iot-service/pom.xml ./energy-iot-service/pom.xml
COPY energy-iot-service/src ./energy-iot-service/src
RUN mvn -B -ntp -e -f energy-iot-service/pom.xml clean package -DskipTests
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/energy-iot-service/target/*.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "mkdir -p /app/certs && printf '%s\\n' \"$MQTT_CA_CERT\" > /app/certs/ca.crt && exec java -jar app.jar"]