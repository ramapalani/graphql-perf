FROM maven:3.8.5-openjdk-11 as build

RUN mkdir -p /app/tmp

COPY src /app/src
COPY pom.xml /app
COPY entry.sh /app/entry.sh
WORKDIR /app
RUN mvn clean install

FROM maven:3.8.5-openjdk-11
COPY --from=build /app /app
WORKDIR /app
CMD ["/bin/sh", "/app/entry.sh"]


