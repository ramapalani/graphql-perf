package common;

/**
 * Encapsulates all GraphQL queries to be used in different test scenarios.
 */
public final class GraphQLQueries {

    public static final String SINGLE_SUBGRAPH_100KB_QUERY = "{\"query\":" +
                "\"query Shows {  " +
                "   shows {    " +
                "       id   " +
                "       title    " +
                "       reviews {      " +
                "           username      " +
                "           starScore      " +
                "       }" +
                "     }" +
                "}\"," +
                "\"operationName\":\"Shows\"" +
            "}";
}
