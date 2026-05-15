FROM maven:3.8.5-openjdk-17-slim AS build
# Copy source code and build the application
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
# Copy the built jar from the build stage
COPY --from=build /app/target/ems-0.0.1-SNAPSHOT.jar ems.jar
# Expose default port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java","-jar","ems.jar"]
