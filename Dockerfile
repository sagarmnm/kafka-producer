FROM java:8
WORKDIR /target
ADD target/spafka-producer-0.0.1-SNAPSHOT.jar spafka-producer-0.0.1-SNAPSHOT.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "spafka-producer-0.0.1-SNAPSHOT.jar"]