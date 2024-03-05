FROM maven:3.8.3-openjdk-17 AS spring-builder
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package

FROM eclipse-temurin:17
WORKDIR /opt/app
COPY --from=spring-builder /usr/src/app/target/storj-1.0.0.jar /opt/app/storj.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/storj.jar"]
