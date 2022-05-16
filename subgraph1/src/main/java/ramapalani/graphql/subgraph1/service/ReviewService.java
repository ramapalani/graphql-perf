package ramapalani.graphql.subgraph1.service;

import ramapalani.graphql.subgraph1.generated.types.Review;
import ramapalani.graphql.subgraph1.generated.types.SubmittedReview;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    Review saveReview(SubmittedReview submittedReview);
    List<Review> saveReviews(List<SubmittedReview> submittedReviews);
    Map<String, List<Review>> reviewsForShows(List<String> showIds);
}
