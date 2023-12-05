FROM openjdk:21

EXPOSE 80

ENV NAMESPACE_URI "http://cmpt436/soap-web-service/productcatalog"

ADD target/productcatalog-1.0.0-SERVICE.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]