package supergraph;

import common.HttpRunner;
import io.gatling.javaapi.core.*;

import static common.ConfigResolver.*;
import static common.GraphQLQueries.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class BasicSimulation extends Simulation {
    ScenarioBuilder scn = scenario("Supergraph 100kb Payload")
          .exec(HttpRunner.getHttpPostBuilder("100kb payload",
                  SINGLE_SUBGRAPH_100KB_QUERY,
                  GLOBAL_LATENCY_MILLISECONDS > 0 ? GLOBAL_LATENCY_MILLISECONDS : SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS));
  {
    setUp(scn.injectOpen(
            rampUsersPerSec(MIN_USER_COUNT)
                    .to(SINGLE_SUBGRAPH_100KB_TPS)
                    .during(TEST_RAMP_SECONDS),
            constantUsersPerSec(SINGLE_SUBGRAPH_100KB_TPS).during(TEST_DURATION_SECONDS)))
        .protocols(HttpRunner.httpProtocol);
  }
}
