package ramapalani.graphql.subgraph1.dataloader;

import ramapalani.graphql.subgraph1.generated.types.Review;
import ramapalani.graphql.subgraph1.service.ReviewService;
import ramapalani.graphql.subgraph1.service.ReviewServiceImpl;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.MappedBatchLoaderWithContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = "reviewsInBatch")
public class ReviewsDataLoader implements MappedBatchLoaderWithContext<String, List<Review>> {

    private final ReviewService reviewService;

    public ReviewsDataLoader(ReviewServiceImpl reviewServiceImpl) {
        this.reviewService = reviewServiceImpl;
    }

    @Override
    public CompletionStage<Map<String, List<Review>>> load(Set<String> showIds, BatchLoaderEnvironment batchLoaderEnvironment) {
        return CompletableFuture.supplyAsync(() -> reviewService.reviewsForShows(new ArrayList<>(showIds)));
    }
}
