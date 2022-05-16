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

# Run Apache Benchmark Test

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