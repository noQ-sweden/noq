FROM maven:3.9.1-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY ./pom.xml .

RUN mvn -B -X package --file pom.xml -e

COPY ./src ./src
 
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
EXPOSE 8080

COPY --from=builder /app/target/backend-*.jar /app/backend.jar
ENTRYPOINT ["java", "-jar", "/app/backend.jar" ]