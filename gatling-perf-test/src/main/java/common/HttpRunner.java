package common;

import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static common.ConfigResolver.BASE_URL;
import static common.ConfigResolver.GRAPH_URI;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

/**
 * HTTPRunner class encapsulates HTTP protocol to be used with all the requests made to the
 * system under test. It also contains different types of requests for the system under
 * test which can be used by various scenarios.
 */
public class HttpRunner {

    private static final Logger logger = LoggerFactory.getLogger(HttpRunner.class);

    public static HttpProtocolBuilder httpProtocol = http
            .baseUrl(BASE_URL)
            .header("Content-Type", "application/json")
            .shareConnections();

    public static HttpRequestActionBuilder getHttpPostBuilder(String requestName, String requestBody) {
        return getHttpPostBuilder(requestName, requestBody, 0);
    }

    public static HttpRequestActionBuilder getHttpPostBuilder(String requestName, String requestBody, Integer latency) {
        return http(requestName)
                .post(GRAPH_URI)
                .requestTimeout(Duration.ofMillis(600000))
                .header("x_latency", latency != null ? latency.toString() : "0")
                .body(StringBody(requestBody))
                .check(status().is(200));
    }
}
