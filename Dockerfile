FROM java:8
WORKDIR /target
ADD target/kafka-producer-0.0.1-SNAPSHOT.jar kafka-producer-0.0.1-SNAPSHOT.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "kafka-producer-0.0.1-SNAPSHOT.jar"]