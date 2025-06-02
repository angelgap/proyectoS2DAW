FROM maven:3.9.5-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Vee-0.0.1-SNAPSHOT.jar app.jar
COPY wallet ./wallet
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
