#!/bin/bash
kubectl delete deployment apollo-router subgraph1
kubectl delete svc apollo-router subgraph1
kubectl delete cm supergraph routerconfig
kubectl delete job gatling-perf-test || echo Job gatling-perf-test not found
