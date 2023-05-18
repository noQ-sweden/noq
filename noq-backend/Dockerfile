FROM maven:3.9.1-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY ./pom.xml .
COPY ./src ./src

RUN mvn -B package --file pom.xml -e

 
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
EXPOSE 8080

COPY --from=builder /app/target/backend-*.jar /app/backend.jar
ENTRYPOINT ["java", "-jar", "/app/backend.jar" ]