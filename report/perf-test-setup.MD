# Perf Test Setup

* Apollo Gateway (or Apollo Router), Subgraph1 and Gatling tests are all deployed in the same Kubernetes Namespace.
* All these pods are running in the same Kubernetes Node (EC2 Instance: c5.12xlarge)
* Every iterations, running pods are deleted and restarted using these [cleanup.sh](../k8s/cleanup.sh) & [create.sh](../k8s/create.sh)
* Each iteration is run for 10 minutes with a ramp up of 1 min starting from 1TPS to desired TPS of the iteration
* Response Payload size is 100kb.  This is the [query](../gatling-perf-test/src/main/java/common/GraphQLQueries.java) executed.

### Apollo Gateway(or Router) 
* Pod - 1
* CPU - 1500m (request) & 2000m (limit)
* Memory - 3Gi (request) & 4Gi (limit)
* [gateway-deployment.yaml](../k8s/gateway-deployment.yaml)
### Subgraph1
* Pods - 3
* [ubgraph1-deployment.yaml](../k8s/subgraph1-deployment.yaml)
### Gatling Perf Test
* Gateway Test Job: [Job Object](../k8s/gatling-perf-test-job.yaml)
* Router Test Job: [Job Object](../k8s/gatling-perf-test-router-job.yaml)

