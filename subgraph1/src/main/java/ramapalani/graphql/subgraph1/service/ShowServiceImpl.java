package ramapalani.graphql.subgraph1.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ramapalani.graphql.subgraph1.generated.types.Show;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class ShowServiceImpl implements ShowService {

    private static final Logger logger = LoggerFactory.getLogger(ShowServiceImpl.class);
    private List<Show> shows;
    private Map<String, List<Show>> actorShowMap;
    
    public ShowServiceImpl() {
        //This data should be changed in sync with
        //https://github.intuit.com/rpalaniappan/gql-test-subgraph2/blob/master/app/src/main/java/com/intuit/v4/apimanagement/gqltestsubgraph2/service/ActorServiceImpl.java#L24
        Show strangerThings = Show.newBuilder().id("1").title("Stranger Things").releaseYear(2016).build();
        Show ozark = Show.newBuilder().id("2").title("Ozark").releaseYear(2017).build();
        Show theCrown = Show.newBuilder().id("3").title("The Crown").releaseYear(2016).build();
        Show deadToMe = Show.newBuilder().id("4").title("Dead to Me").releaseYear(2019).build();
        Show orange = Show.newBuilder().id("5").title("Orange is the New Black").releaseYear(2013).build();
        List<Show> manuallyCreated = Arrays.asList(
                strangerThings,
                ozark, theCrown,
                deadToMe, orange
        );
        List<Show> showsFromCsv = loadShows("shows.csv");
        shows = new ArrayList<>();
        shows.addAll(manuallyCreated);
        shows.addAll(showsFromCsv);

        actorShowMap = new HashMap<>();
        actorShowMap.put("1", Arrays.asList(strangerThings,theCrown, deadToMe));
        actorShowMap.put("2", Arrays.asList(strangerThings,theCrown));
        actorShowMap.put("3", Arrays.asList(strangerThings,deadToMe,orange));
        actorShowMap.put("4", Arrays.asList(ozark,orange));
        actorShowMap.put("5", Arrays.asList(ozark,deadToMe));
        actorShowMap.put("6", Arrays.asList(ozark,theCrown,orange));
    }

    @Override
    public List<Show> shows(Integer latency) {
        addLatency(latency);
        return shows;
    }

    @Override
    public Optional<Show> showById(String id, Integer latency) {
        addLatency(latency);
        return shows.stream().filter(show -> show.getId().equals(id)).findFirst();
    }

    private void addLatency(Integer latency) {
        if (latency != null && latency > 0) {
            try {
                Thread.sleep(latency);
            } catch (InterruptedException e) {
                logger.info("Interrupted", e);
            }
        }
    }

    private List<Show> loadShows(String fileName) {
        return loadCSV(Show.class, fileName);
    }
    private <T> List<T> loadCSV(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            InputStream inputStream = new ClassPathResource(fileName).getInputStream();
            MappingIterator<T> readValues = 
              mapper.readerFor(type).with(bootstrapSchema).readValues(inputStream);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
