#!/bin/sh

JAVA_OPTS="${JAVA_OPTS} -XX:+UnlockExperimentalVMOptions \
  -XX:+UseG1GC \
  -XX:+UseStringDeduplication \
  -XX:MinRAMPercentage=50.0 \
  -XX:MaxRAMPercentage=80.0 \
  -XshowSettings:vm"

# use app dir for tmp dir
JAVA_OPTS="${JAVA_OPTS} -Djava.io.tmpdir=/app/tmp"

exec java $JAVA_OPTS -jar /app/subgraph1.jar
