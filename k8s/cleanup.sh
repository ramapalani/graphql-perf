#!/bin/bash
kubectl delete deployment apollo-gateway subgraph1
kubectl delete job ab-perf-test
kubectl delete svc apollo-gateway subgraph1
kubectl delete cm supergraph
