FROM openjdk:21

EXPOSE 80

ADD target/ProductCatalog-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]