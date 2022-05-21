#!/bin/bash

export DIR=`dirname $0`
export BASE=${DIR}/..
TPS=(1 2 4 8 16 32 64 128)

for t in ${TPS[@]}; do
    echo "********* TPS=${t} **********"
    ${BASE}/cleanup-router.sh
    ${BASE}/create-router.sh
    ${BASE}/perf-router.sh ${t}-tps.yaml
    sleep 120
done