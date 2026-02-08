# syntax=docker/dockerfile:1
FROM eclipse-temurin:25.0.2_10-jdk-alpine AS build
WORKDIR /src
COPY . .
RUN ./mvnw -q -DskipTests package || mvn -q -DskipTests package
RUN mkdir -p /out && cp target/*SNAPSHOT.jar /out/app.jar

FROM gcr.io/distroless/java21-debian12
WORKDIR /app
COPY --from=build /out/app.jar /app/app.jar
USER 65532:65532
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app/app.jar"]
