# Apollo-Gateway 100KB Response Payload Perf Results

## Perf Setup
* Response Payload = 100kb
More details are in this page: [Perf Setup](./perf-test-setup.MD)

## Synthetic Latency of 100ms

* Max TPS that could be achieved for this load is 78 transactions per second (TPS).
* Supergraph scales well without any degration till 32 TPS.  
* Time spent in the supergraph(supergraph time - subgraph time)
  * <=32 TPS: 10-12 ms
  * 32 < TPS >= 78: 353-1790ms (upto 2.6x more than the time spent in subgraph)

* System metrics CPU and Memory didn't breach the "request" values provided.

| System Metric | Max Usage in test | Request Value | Limit Value |
| --- | --- | --- | --- |
| CPU (millicore)| 1260 | 1500 | 2000 |
| Memory (MB) | 246 | 3000M | 4000M |



![Response Time](./100msLatency/ResponseTime100kb100ms.png)

| Transactions Per Second | Synthetic Latency | Client Response Time P95 (ms) | Supergraph Response Time P95 (ms) | Subgraph Response Time P95 (ms) | Time spent in Supergraph (ms) | CPU (millicore) | Memory (MB) | Screenshot |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 100 | 165 | 160 | 148 | 12 | 25 | 80 | [1tps-time.png](./100msLatency/1tps-time.png) \| [1tps-sys.png](./100msLatency/1tps-sys.png)
| 2 | 100 | 160 | 156 | 144 | 12 | 40 | 80 | [2tps-time.png](./100msLatency/2tps-time.png) \| [2tps-sys.png](./100msLatency/2tps-sys.png)
| 4 | 100 | 156 | 152 | 141 | 11 | 73 | 83 | [4tps-time.png](./100msLatency/4tps-time.png) \| [4tps-sys.png](./100msLatency/4tps-sys.png)
| 16 | 100 | 154 | 150 | 139 | 11 | 309 | 88 | [16tps-time.png](./100msLatency/16tps-time.png) \| [16tps-sys.png](./100msLatency/16tps-sys.png)
| 32 | 100 | 154 | 150 | 138 | 12 | 722 | 146 | [32tps-time.png](./100msLatency/32tps-time.png) \| [32tps-sys.png](./100msLatency/32tps-sys.png)
| 64 | 100 | 864 | 547 | 194 | 353 | 1024 | 182 | [64tps-time.png](./100msLatency/64tps-time.png) \| [64tps-sys.png](./100msLatency/64tps-sys.png)
| 128 | 100 | timeout error | - | - | - | - | - | [128tps-time.png](./100msLatency/128tps-time.png) \| [128tps-sys.png](./100msLatency/128tps-sys.png)
| 96 | 100 | timeout error | - | - | - | - | - | [96tps-time.png](./100msLatency/96tps-time.png) \| [96tps-sys.png](./100msLatency/96tps-sys.png)
| 80 | 100 | timeout error | - | - | - | - | - | [80tps-time.png](./100msLatency/80tps-time.png) \| [80tps-sys.png](./100msLatency/80tps-sys.png)
| 72 | 100 | 1775 | 1142 | 345 | 797 | 1175 | 205 | [72tps-time.png](./100msLatency/72tps-time.png) \| [72tps-sys.png](./100msLatency/72tps-sys.png)
| 76 | 100 | 3402 | 2150 | 582 | 1568 | 1250 | 249 | [76tps-time.png](./100msLatency/76tps-time.png) \| [76tps-sys.png](./100msLatency/76tps-sys.png)
| 78 | 100 | 3829 | 2467 | 677 | 1790 | 1260 | 246 | [78tps-time.png](./100msLatency/78tps-time.png) \| [78tps-sys.png](./100msLatency/78tps-sys.png)

