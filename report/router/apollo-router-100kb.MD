# Apollo-Router 100KB Response Payload Perf Results

## Perf Setup
* Response Payload = 100kb
More details are in this page: [Perf Setup](../perf-test-setup.MD)

## Synthetic Latency of 100ms


### Test Result
![Response Time](./100msLatency/ResponseTime100kb100ms.png)

| Transactions Per Second | Synthetic Latency | Client Response Time P95 (ms) | Supergraph Response Time P95 (ms) | Subgraph Response Time P95 (ms) | Time spent in Supergraph (ms) | CPU (millicore) | Memory (MB) | Screenshot |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 100 | 159 | 21:54 | 
| 2 | 100 | 152 | 22:12 | 
| 4 | 100 | 143 | 22:27 | 
| 8 | 100 | 142 | 22:41 | 
| 16 | 100 | 144 | 22:56 |
| 32 | 100 | 143 | 23:10 |
| 64 | 100 | 146 | 23:24 |
| 128 | 100 | 148 | 23:39 |
| 256 | 100 | 159 | 07:14 |


## Synthetic Latency of 5s

### Test Result
![Response Time](./5000msLatency/ResponseTime100kb5s.png)

| Transactions Per Second  | Client Response Time P95 (ms) | Supergraph Response Time P95 (ms) | Subgraph Response Time P95 (ms) | Time spent in Supergraph (ms) | CPU (millicore) | Memory (MB) | Screenshot |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 256 | failed | 00:57
| 128 | 47553 | 01:12