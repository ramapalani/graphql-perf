#!/bin/bash

export DIR=`dirname $0`
export BASE=${DIR}/..
TPS=(1 2 4 8 16 32 64 128)

for t in ${TPS[@]}; do
    echo "********* TPS=${t} **********"
    ${BASE}/cleanup.sh
    ${BASE}/create.sh
    ${BASE}/perf.sh ${t}-tps.yaml
    sleep 120
done