FROM maven:3.8.5-openjdk-11 AS build

COPY pom.xml pom.xml
COPY src src
COPY entry.sh entry.sh
RUN mvn versions:set -DnewVersion=build
RUN mvn -U clean install
RUN mkdir /app
RUN mv target/subgraph1-build.jar /app/subgraph1.jar
RUN mv entry.sh /app

FROM openjdk:11

RUN mkdir -p /app/tmp
COPY --from=build /app /app
CMD ["/bin/sh", "/app/entry.sh"]
