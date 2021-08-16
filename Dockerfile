FROM openjdk:15-alpine as builder
LABEL maintainer="eltumerabe@gmail.com"
VOLUME /bookstore
ADD /target/*.jar bookstore.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar","/bookstore.jar"]