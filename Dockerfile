FROM openjdk:11

ARG JAR
COPY ./${JAR} clash-bot-event-stream.jar

ENTRYPOINT ["java","-jar","clash-bot-event-stream.jar"]