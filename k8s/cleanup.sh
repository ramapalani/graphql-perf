#!/bin/bash
kubectl delete deployment apollo-gateway subgraph1
kubectl delete svc apollo-gateway subgraph1
kubectl delete cm supergraph
kubectl delete job ab-perf-test || echo Job ab-perf-test not found
kubectl delete job gatling-perf-test || echo Job gatling-perf-test not found
