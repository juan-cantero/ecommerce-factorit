FROM openjdk:17-alpine

MAINTAINER Juan Cantero "juan.cantero@outlook.com"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar

CMD ["java","-Dspring.profiles.active=docker","-jar","ecommerce.jar"]