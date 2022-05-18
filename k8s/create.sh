#!/bin/bash

# export KUBECONFIG=xxx
# cd k8s
# Deploy Supergraph and Subgraph
kubectl create configmap supergraph --from-file=../supergraph.graphql
kubectl apply -f gateway-deployment.yaml -f subgraph1-deployment.yaml
kubectl apply -f gateway-service.yaml -f subgraph1-service.yaml

kubectl get deployments,pods,services,configmap

set -x
sleep 30
set +x

# Run Gatling Perf test
kubectl delete -f gatling-perf-test-job.yaml; kubectl apply -f gatling-perf-test-job.yaml

job=$(kubectl get pods -l job-name=gatling-perf-test --field-selector=status.phase=Running --output=jsonpath='{.items[*].metadata.name}')
kubectl logs -f $job


# Run Apache Benchmark test
# kubectl delete -f ab-perf-test-job.yaml; kubectl apply -f ab-perf-test-job.yaml

# job=$(kubectl get pods -l job-name=ab-perf-test --field-selector=status.phase=Running --output=jsonpath='{.items[*].metadata.name}')
# kubectl logs -f $job

# Port forward Apollo Gateway Supergraph port
#supergraph=$(kubectl get pods -l app=apollo-gateway --field-selector=status.phase=Running --output=jsonpath='{.items[*].metadata.name}')
#kubectl port-forward $supergraph 4000:4000

# Run this in another terminal to keep the port-forward open
# while true ; do nc -vz 127.0.0.1 4000 ; sleep 10 ; done

