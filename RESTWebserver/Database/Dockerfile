FROM amazoncorretto:17

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/Database-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} Database.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Database.jar"]