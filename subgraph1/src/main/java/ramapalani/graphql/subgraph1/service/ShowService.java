package ramapalani.graphql.subgraph1.service;

import ramapalani.graphql.subgraph1.generated.types.Show;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

public interface ShowService {

    List<Show> shows(Integer latency);
    Optional<Show> showById(String id, Integer latency);

}
