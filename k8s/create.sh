#!/bin/bash

export DIR=`dirname $0`

# export KUBECONFIG=xxx
# cd k8s
# Deploy Supergraph and Subgraph
kubectl create configmap supergraph --from-file=$DIR/../supergraph.graphql
cat $DIR/gateway-deployment.yaml | kubectl apply -f -
cat $DIR/subgraph1-deployment.yaml | kubectl apply -f -
cat $DIR/gateway-service.yaml | kubectl apply -f -
cat $DIR/subgraph1-service.yaml | kubectl apply -f -

kubectl get deployments,pods,services,configmap

#Wait for apollo-gateway pod to be ready
printf "Waiting for apollo-gateway pod to be ready " 
while [[ $(kubectl get pods -l app=apollo-gateway -o 'jsonpath={..status.conditions[?(@.type=="Ready")].status}') != "True" ]]; 
do 
    printf '.'
    sleep 1
done
echo "Pod Ready"
kubectl get pods -l app=apollo-gateway

# $DIR/perf.sh gatling-perf-test-job.yaml


# Run Apache Benchmark test
# kubectl delete -f ab-perf-test-job.yaml; kubectl apply -f ab-perf-test-job.yaml

# job=$(kubectl get pods -l job-name=ab-perf-test --field-selector=status.phase=Running --output=jsonpath='{.items[*].metadata.name}')
# kubectl logs -f $job

# Port forward Apollo Gateway Supergraph port
#supergraph=$(kubectl get pods -l app=apollo-gateway --field-selector=status.phase=Running --output=jsonpath='{.items[*].metadata.name}')
#kubectl port-forward $supergraph 4000:4000

# Run this in another terminal to keep the port-forward open
# while true ; do nc -vz 127.0.0.1 4000 ; sleep 10 ; done

