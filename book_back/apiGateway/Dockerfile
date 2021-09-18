FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY ./target/apiGateway-1.0.jar apiGateway-1.0.jar
ENTRYPOINT ["java","-jar","apiGateway-1.0.jar"]