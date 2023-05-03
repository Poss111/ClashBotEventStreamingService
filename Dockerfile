FROM openjdk:11

ARG JAR
COPY ./build/libs/* clash-bot-event-stream.jar

RUN ls -lha

ENTRYPOINT ["java","-jar","clash-bot-event-stream.jar"]