# Stage 1: Build the application
FROM eclipse-temurin:17-jdk as builder
WORKDIR /opt/app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw clean package -DskipTests
RUN cp target/*.jar app.jar

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /opt/app/app.jar /app/
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
