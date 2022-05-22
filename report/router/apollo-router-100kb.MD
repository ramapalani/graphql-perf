# Apollo-Router 100KB Response Payload Perf Results

## Perf Setup
* Response Payload = 100kb
More details are in this page: [Perf Setup](../perf-test-setup.MD)

## Synthetic Latency of 100ms


### Test Result
![Response Time](./100msLatency/ResponseTime100kb100ms.png)

| Transactions Per Second | Synthetic Latency | Client Response Time P95 (ms) | Supergraph Response Time P95 (ms) | Subgraph Response Time P95 (ms) | Time spent in Supergraph (ms) | CPU (millicore) | Memory (MB) | Screenshot |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 100 | 159 | 21:54 | 148 | 12 | 25 | 80 | [1tps-time.png](./100msLatency/1tps-time.png) \| [1tps-sys.png](./100msLatency/1tps-sys.png)
| 2 | 100 | 152 | 22:12 | 144 | 12 | 40 | 80 | [2tps-time.png](./100msLatency/2tps-time.png) \| [2tps-sys.png](./100msLatency/2tps-sys.png)
| 4 | 100 | 143 | 22:27 | 141 | 11 | 73 | 83 | [4tps-time.png](./100msLatency/4tps-time.png) \| [4tps-sys.png](./100msLatency/4tps-sys.png)
| 8 | 100 | 142 | 22:41 | 139 | 11 | 309 | 88 | [8tps-time.png](./100msLatency/8tps-time.png) \| [8tps-sys.png](./100msLatency/8tps-sys.png)
| 16 | 100 | 144 | 22:56 | 139 | 11 | 309 | 88 | [16tps-time.png](./100msLatency/16tps-time.png) \| [16tps-sys.png](./100msLatency/16tps-sys.png)
| 32 | 100 | 143 | 23:10 | 138 | 12 | 722 | 146 | [32tps-time.png](./100msLatency/32tps-time.png) \| [32tps-sys.png](./100msLatency/32tps-sys.png)
| 64 | 100 | 146 | 23:24 | 194 | 353 | 1024 | 182 | [64tps-time.png](./100msLatency/64tps-time.png) \| [64tps-sys.png](./100msLatency/64tps-sys.png)
| 128 | 100 | 148 | 23:39 | - | - | - | - | [128tps-time.png](./100msLatency/128tps-time.png) \| [128tps-sys.png](./100msLatency/128tps-sys.png)
| 256 | 100 | 159 | 07:14 | - | - | - | - | [256tps-time.png](./100msLatency/256tps-time.png) \| [256tps-sys.png](./100msLatency/256tps-sys.png)
| 512 | 100 | Single pod gatling test not able to generate this load | - | - | - | - | - | [512tps-time.png](./100msLatency/512tps-time.png) \| [512tps-sys.png](./100msLatency/512tps-sys.png)


## Synthetic Latency of 5s

### Test Result
![Response Time](./5000msLatency/ResponseTime100kb5s.png)

| Transactions Per Second  | Client Response Time P95 (ms) | Supergraph Response Time P95 (ms) | Subgraph Response Time P95 (ms) | Time spent in Supergraph (ms) | CPU (millicore) | Memory (MB) | Screenshot |
| --- | --- | --- | --- | --- | --- | --- | --- |
