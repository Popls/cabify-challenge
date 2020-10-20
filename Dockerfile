FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 9091:9091
VOLUME /tmp
ADD /target/carpooling-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]