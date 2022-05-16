#!/bin/bash

# Set environment variables to override this
# 'ab' doesn't have a direct parameter to set Transactions per second(TPS)
# Tweak these two variable to achieve desired TPS
# 'ab' summary report has mean TPS
# 100 concurrent users with 1000 requests got me around 20tps
CONCURRENT_USERS="${CONCURRENT_USERS:=1}"
NUMBER_OF_REQUESTS="${NUMBER_OF_REQUESTS:=1}"

URL="${URL:=http://apollo-gateway:4000/graphql}"

echo CONCURRENT_USERS=${CONCURRENT_USERS}
echo NUMBER_OF_REQUESTS=${NUMBER_OF_REQUESTS}
echo URL=${URL}

/usr/bin/ab -k -c ${CONCURRENT_USERS} \
    -n ${NUMBER_OF_REQUESTS} \
    -T application/json -p post.json \
    ${URL}
sleep 3600