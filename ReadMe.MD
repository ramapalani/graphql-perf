# Performance Benchmark
* [Apollo-Gateway 100KB Payload](./report/gateway/apollo-gateway-100kb.MD)
* [Apollo-Router 100KB Payload](./report/router/apollo-router-100kb.MD)

# Run Locally

Pre-Req
* Make
* docker
* docker-compose

```
make docker-up
```
This command will build Apollo Gateway, a DGS based subgraph and an Apache Benchmark perf test and run all these containers.

## Container Images

These container images are published to docker hub

```
docker pull ramapalaniappan436/apollo-gateway:0.42.0
docker pull ramapalaniappan436/subgraph1:latest
docker pull ramapalaniappan436/ab-perf-test:latest
```

<details><summary>Run Apache Benchmark Test</summary>

Tweak these environment variable in ab-perf-test container to run a different load
```
# Number of concurrent users
CONCURRENT_USERS=10

# Total Number of requests to be run 
NUMBER_OF_REQUESTS=1000

# Add synthetic latency to subgraph response
# Value in millisecond
SYNTHETIC_LATENCY=100
```
</details>

# Run in Kubernetes

Pre-Req
* kubectl
* Access to a Kubernetes Namespace and able to create these objects: deployment, pod, service, job and configmap

```
# Deletes all Kubernetes objects that this project created
./k8s/cleanup.sh

# Creates apollo-gateway, subgraph and wait till the apollo-gateway pod is ready
./k8s/create.sh

# Runs a Gatling perf test 
./k8s/perf.sh
```