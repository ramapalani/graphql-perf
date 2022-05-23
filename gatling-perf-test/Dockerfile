FROM maven:3.8.5-openjdk-11 as build

COPY src src
COPY pom.xml pom.xml
RUN mvn dependency:resolve
RUN mvn clean install
RUN mkdir -p /app/tmp
COPY target/gatling-maven-plugin-demo-java-3.7.6-jar-with-dependencies.jar /app/gatling-perf-test.jar
COPY entry.sh /app/entry.sh

# https://gatling.io/docs/gatling/reference/current/core/operations
COPY sysctl.conf /etc/sysctl.conf
COPY limits.conf /tmp
RUN cat /tmp/limits.conf >> /etc/security/limits.conf

FROM openjdk:11
COPY --from=build /app /app
COPY --from=build /etc/sysctl.conf /etc/sysctl.conf
COPY --from=build /etc/security/limits.conf /etc/security/limits.conf

WORKDIR /app
CMD ["/bin/sh", "/app/entry.sh"]


