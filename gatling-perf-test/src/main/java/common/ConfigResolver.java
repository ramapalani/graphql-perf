package common;

/**
 * Reads various config properties from the system properties. Mainly used
 * here to read all the secrets. Later, can be extended to read other configs
 * as well like from .conf files.
 */
public class ConfigResolver {
    public static final String BASE_URL = System.getProperty("BASE_URL", "http://apollo-gateway:4000");
    public static final Integer MIN_USER_COUNT = Integer.valueOf(System.getProperty("MIN_USER_COUNT", "1"));
    public static final Integer TEST_RAMP_SECONDS = Integer.valueOf(System.getProperty("TEST_RAMP_SECONDS", "1"));
    public static final Integer TEST_DURATION_SECONDS = Integer.valueOf(System.getProperty("TEST_DURATION_SECONDS", "1"));

    // Configs used for setting the load percentage for different test scenarios
    public static final Double TOTAL_TPS = Double.parseDouble(System.getProperty("TOTAL_TPS", "20"));
    // If GLOBAL_LATENCY_MILLISECONDS is set to a value greater than zero, it will take precedence over other read latencies defined as per the payload size
    public static final Integer GLOBAL_LATENCY_MILLISECONDS = Integer.valueOf(System.getProperty("GLOBAL_LATENCY_MILLISECONDS", "100"));
    public static final Integer SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS = Integer.valueOf(System.getProperty("SINGLE_SUBGRAPH_100KB_LATENCY_MILLISECONDS", "0"));
    public static  final Double SINGLE_SUBGRAPH_100KB_LOAD_PERCENTAGE = Double.parseDouble(System.getProperty("SINGLE_SUBGRAPH_100KB_LOAD_PERCENTAGE", "100")) * 0.01d;
    public static  final Double SINGLE_SUBGRAPH_100KB_TPS = TOTAL_TPS * SINGLE_SUBGRAPH_100KB_LOAD_PERCENTAGE;

}
