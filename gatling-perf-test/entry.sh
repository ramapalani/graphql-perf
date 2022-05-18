#!/bin/sh

JAVA_OPTS="${JAVA_OPTS} -XX:+UnlockExperimentalVMOptions \
  -XX:+UseG1GC \
  -XX:+UseStringDeduplication \
  -XX:MinRAMPercentage=50.0 \
  -XX:MaxRAMPercentage=80.0 \
  -XshowSettings:vm"

# use app dir for tmp dir
JAVA_OPTS="${JAVA_OPTS} -Djava.io.tmpdir=/app/tmp"

# Set these as environment values through container runtime
MIN_USER_COUNT="${MIN_USER_COUNT:=1}"
TEST_RAMP_SECONDS="${TEST_RAMP_SECONDS:=1}"
TOTAL_TPS="${TOTAL_TPS:=1}"
TEST_DURATION_SECONDS="${TEST_DURATION_SECONDS:=60}"
SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS="${SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS:=100}"
#GRAPHQL_URL="${GRAPHQL_URL:=http://apollo-gateway:4000/graphql}"

echo Env Values
printenv

exec java $JAVA_OPTS   \
  -DMIN_USER_COUNT=${MIN_USER_COUNT} \
  -DTEST_RAMP_SECONDS=${TEST_RAMP_SECONDS} \
  -DTOTAL_TPS=${TOTAL_TPS} \
  -DTEST_DURATION_SECONDS=${TEST_DURATION_SECONDS} \
  -DSINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS=${SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS} \
  -jar /app/gatling-perf-test.jar \
  -s supergraph.BasicSimulation

