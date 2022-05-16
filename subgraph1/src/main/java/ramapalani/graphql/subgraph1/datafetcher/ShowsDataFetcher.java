package ramapalani.graphql.subgraph1.datafetcher;

import ramapalani.graphql.subgraph1.Constants;
import ramapalani.graphql.subgraph1.generated.DgsConstants;
import ramapalani.graphql.subgraph1.generated.types.Show;
import ramapalani.graphql.subgraph1.service.ShowService;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.internal.DgsRequestData;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDataFetcher {

    private final ShowService showService;
    private static final Logger logger = LoggerFactory.getLogger(ShowsDataFetcher.class);

    public ShowsDataFetcher(ShowService showService) {
        this.showService = showService;
    }

    @DgsQuery(field = DgsConstants.QUERY.Shows)
    public List<Show> shows(@InputArgument(collectionType = String.class) Optional<String> titleFilter,
                            DgsDataFetchingEnvironment dfe,
                            @RequestHeader(name= Constants.X_LATENCY, defaultValue = Constants.DEFAULT_LATENCY)String latency) {
        //Debug
        DgsRequestData requestData = dfe.getDgsContext().getRequestData();
        HttpHeaders httpHeaders = requestData.getHeaders();
        logger.info("Print all request headers");
        httpHeaders.entrySet().stream().forEach(entry -> {
            logger.info (entry.getKey() + "=" + Arrays.toString(entry.getValue().toArray()));
        });
        int addLatencyInMs = Integer.parseInt(latency);
        if (titleFilter.isPresent()) {
            return showService
                    .shows(addLatencyInMs).stream()
                    .filter(show -> show.getTitle().contains(titleFilter.get()))
                    .collect(Collectors.toList());
        }

        return showService.shows(addLatencyInMs);
    }

    @DgsQuery( field = DgsConstants.QUERY.Show )
    public Show show(@InputArgument(name = "id", collectionType = String.class) Optional<String> id,
                     @RequestHeader(name=Constants.X_LATENCY, defaultValue = Constants.DEFAULT_LATENCY)String latency) {
        if (id.isPresent()) {
            int addLatencyInMs = Integer.parseInt(latency);
            Optional<Show> show = showService.showById(id.get(), addLatencyInMs);
            if (show.isPresent()) {
                return show.get();
            }
        }
        return null;
    }
}
