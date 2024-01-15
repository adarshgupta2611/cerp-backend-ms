# Build Stage
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Final Stage
FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/STUDENT-SERVICE.jar STUDENT-SERVICE.jar

CMD ["java", "-jar", "STUDENT-SERVICE.jar"]