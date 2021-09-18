FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY ./target/cart-service-1.0.jar cart-service-1.0.jar
ENTRYPOINT ["java","-jar","cart-service-1.0.jar"]
