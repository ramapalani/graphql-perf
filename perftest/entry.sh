#!/bin/bash

# Set environment variables to override this
# 'ab' doesn't have a direct parameter to set Transactions per second(TPS)
# Tweak these two variable to achieve desired TPS
# 'ab' summary report has mean TPS
CONCURRENT_USERS="${CONCURRENT_USERS:=1}"
NUMBER_OF_REQUESTS="${NUMBER_OF_REQUESTS:=1}"

URL="${URL:=http://apollo-gateway:4000/graphql}"
# Number of milliseconds that the subgraph will wait
# before returning the response
SYNTHETIC_LATENCY="${SYNTHETIC_LATENCY=5000}"

echo CONCURRENT_USERS=${CONCURRENT_USERS}
echo NUMBER_OF_REQUESTS=${NUMBER_OF_REQUESTS}

echo URL=${URL}
echo SYNTHETIC_LATENCY=${SYNTHETIC_LATENCY}

/usr/bin/ab -k -c ${CONCURRENT_USERS} \
    -n ${NUMBER_OF_REQUESTS} \
    -H "x_latency: $SYNTHETIC_LATENCY" \
    -T application/json -p post.json \
    ${URL}
# Sleep for 30 min, so that ab can complete the test
sleep 1800000