#!/bin/bash

export DIR=`dirname $0`

# export KUBECONFIG=xxx
# cd k8s
# Deploy Supergraph and Subgraph
kubectl create configmap supergraph --from-file=$DIR/../supergraph.graphql
kubectl create configmap routerconfig --from-file=$DIR/../router/router.yaml
cat $DIR/router-deployment.yaml | kubectl apply -f -
cat $DIR/subgraph1-deployment.yaml | kubectl apply -f -
cat $DIR/router-service.yaml | kubectl apply -f -
cat $DIR/subgraph1-service.yaml | kubectl apply -f -

kubectl get deployments,pods,services,configmap

#Wait for apollo-router pod to be ready
printf "Waiting for apollo-router pod to be ready " 
while [[ $(kubectl get pods -l app=apollo-router -o 'jsonpath={..status.conditions[?(@.type=="Ready")].status}') != "True" ]]; 
do 
    printf '.'
    sleep 1
done
echo "Pod Ready"
kubectl get pods -l app=apollo-router

# $DIR/perf.sh gatling-perf-test-job.yaml
