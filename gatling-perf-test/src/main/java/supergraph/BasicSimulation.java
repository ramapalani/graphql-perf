package supergraph;

import common.ConfigResolver;
import common.HttpRunner;
import io.gatling.javaapi.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static common.ConfigResolver.*;
import static common.GraphQLQueries.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class BasicSimulation extends Simulation {
    private static final Logger logger = LoggerFactory.getLogger(BasicSimulation.class);
    private int latency = GLOBAL_LATENCY_MILLISECONDS > 0 ? GLOBAL_LATENCY_MILLISECONDS : SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS;
    ScenarioBuilder scn = scenario("Supergraph 100kb Payload")
          .exec(HttpRunner.getHttpPostBuilder("100kb payload",
                  SINGLE_SUBGRAPH_100KB_QUERY,
                  latency));
  {
          System.out.println("************ base_url=" + BASE_URL);
          System.out.println("************ synthetic latency=" + latency);
    setUp(scn.injectOpen(
            rampUsersPerSec(MIN_USER_COUNT)
                    .to(SINGLE_SUBGRAPH_100KB_TPS)
                    .during(TEST_RAMP_SECONDS),
            constantUsersPerSec(SINGLE_SUBGRAPH_100KB_TPS).during(TEST_DURATION_SECONDS)))
        .protocols(HttpRunner.httpProtocol);
  }
}
