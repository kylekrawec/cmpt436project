FROM openjdk:21

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/ProductCatalog-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} myapp.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]