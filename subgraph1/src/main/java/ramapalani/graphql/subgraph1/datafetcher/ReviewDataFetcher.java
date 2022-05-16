package ramapalani.graphql.subgraph1.datafetcher;

import ramapalani.graphql.subgraph1.dataloader.ReviewsDataLoader;
import ramapalani.graphql.subgraph1.generated.DgsConstants;
import ramapalani.graphql.subgraph1.generated.types.Review;
import ramapalani.graphql.subgraph1.generated.types.Show;
import ramapalani.graphql.subgraph1.generated.types.SubmittedReview;
import ramapalani.graphql.subgraph1.service.ReviewService;
import ramapalani.graphql.subgraph1.service.ReviewServiceImpl;
import com.netflix.graphql.dgs.*;
import org.dataloader.DataLoader;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ReviewDataFetcher {

    private final ReviewService reviewService;

    public ReviewDataFetcher(ReviewServiceImpl reviewServiceImpl) {
        this.reviewService = reviewServiceImpl;
    }

    @DgsMutation(field = DgsConstants.MUTATION.AddReview)
    public Review addReview(@InputArgument(name = "review", collectionType = SubmittedReview.class) Optional<SubmittedReview> submittedReview) {
        if (submittedReview.isPresent()) {
            return reviewService.saveReview(submittedReview.get());
        }
        return null;
    }

    @DgsMutation( field = DgsConstants.MUTATION.AddReviews)
    public List<Review> addReviews(@InputArgument(name = "reviews", collectionType = List.class) List<SubmittedReview> submittedReviews) {
        return reviewService.saveReviews(submittedReviews);
    }

    @DgsData(parentType = DgsConstants.SHOW.TYPE_NAME, field = DgsConstants.SHOW.Reviews)
    public CompletableFuture<List<Review>> reviews(DgsDataFetchingEnvironment dfe) {
        DataLoader<String, List<Review>> reviewsDataLoader = dfe.getDataLoader(ReviewsDataLoader.class);
        Show show = dfe.getSource();
        return reviewsDataLoader.load(show.getId());
    }
}
