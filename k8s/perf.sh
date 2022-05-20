#!/bin/bash
export DIR=`dirname $0`
# Run Gatling Perf test
file=$1
file="${file:=$DIR/gatling-perf-test-job.yaml}"
# kubectl delete -f $file;
kubectl apply -f $file
printf "Waiting for gatling-perf-test pod to be ready "
while [[ $(kubectl get pods -l job-name=gatling-perf-test -o 'jsonpath={..status.conditions[?(@.type=="Ready")].status}') != "True" ]];
do
    printf '.'
    sleep 1
done
echo "Pod Ready"
job=$(kubectl get pods -l job-name=gatling-perf-test --output=jsonpath='{.items[*].metadata.name}')
echo $job
kubectl logs -f $job