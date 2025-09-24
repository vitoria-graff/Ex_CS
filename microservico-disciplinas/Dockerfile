# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /application
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /application
COPY --from=build /application/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/application/app.jar"]
