#!/bin/bash

export KUBECONFIG=xxx
cd k8s
kubectl create configmap supergraph --from-file=../supergraph.graphql
kubectl apply -f .

kubectl get deployments,pods,services,jobs,configmap


kubectl delete deployment apollo-gateway subgraph1
kubectl delete job ab-perf-test
kubectl delete svc apollo-gateway subgraph1
kubectl delete cm supergraph
