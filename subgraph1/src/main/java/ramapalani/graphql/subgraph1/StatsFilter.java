// copied from https://stackoverflow.com/questions/42857658/how-to-log-time-taken-by-rest-web-service-in-spring-boot
package ramapalani.graphql.subgraph1;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StatsFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // empty
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Instant start = Instant.now();
        try {
            chain.doFilter(req, resp);
        } finally {
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            LOGGER.info("uri={}, elapsedTime={}", ((HttpServletRequest) req).getRequestURI(),  time);
        }
    }

    @Override
    public void destroy() {
        // empty
    }
}