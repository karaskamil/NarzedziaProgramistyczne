 FROM maven:3.9.11-amazoncorretto-24
 WORKDIR /lista1
 COPY pom.xml ./pom.xml
 COPY src/main/resources/appsettings.docker.properties ./appsettings.properties
 COPY src ./src
 COPY orders ./orders
 RUN mvn clean package
 CMD ["java", "-jar", "target/lista1.jar"]