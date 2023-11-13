FROM maven:3.9.1-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY ./pom.xml .
COPY ./src ./src

RUN mkdir /prod-config
COPY ./prod-props /prod-config

RUN mvn -B package --file pom.xml -e


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
EXPOSE 8080

COPY --from=builder /app/target/noq-backend-*.jar /app/noq-backend.jar
COPY --from=builder /prod-config /app

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/noq-backend.jar", "--spring.config.location=/app/application.yaml"]
