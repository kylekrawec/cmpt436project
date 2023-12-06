FROM amazoncorretto:17

VOLUME /tmp

EXPOSE 8081

ARG JAR_FILE=target/ProductCatalog-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} ProductionCatalog.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ProductionCatalog.jar"]