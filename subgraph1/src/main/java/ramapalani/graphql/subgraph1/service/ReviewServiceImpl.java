package ramapalani.graphql.subgraph1.service;

import ramapalani.graphql.subgraph1.generated.types.Review;
import ramapalani.graphql.subgraph1.generated.types.SubmittedReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final static Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final Map<String, List<Review>> reviews = new HashMap<>();

    public ReviewServiceImpl() {
        loadReviews();
    }

    @Override
    public Review saveReview(SubmittedReview submittedReview) {
        List<Review> reviewsForShow = reviews.computeIfAbsent(submittedReview.getShowId(),
                key -> new ArrayList<Review>());
        Review review = Review.newBuilder()
                .username(submittedReview.getUsername())
                .starScore(submittedReview.getStarScore())
//                .submittedDate(OffsetDateTime.now())
                .build();
        reviewsForShow.add(review);
        logger.info("Review added {}", review);
        return review;
    }

    @Override
    public List<Review> saveReviews(List<SubmittedReview> submittedReviews) {
        return submittedReviews.stream().map(submittedReview -> saveReview(submittedReview)).collect(Collectors.toList());
    }

    public Map<String, List<Review>> reviewsForShows(List<String> showIds) {
        return reviews.entrySet().stream().
                filter(entry -> showIds.contains(entry.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void loadReviews() {
        //Generate some random reviews
        for (int i=0; i<2000;i++) {
            reviews.put(Integer.toString(i),getSampleReviews());
        }
    }

    private List<Review> getSampleReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(getSampleReview());
        return reviews;
    }

    private Review getSampleReview() {
        return new Review("test-user", 4
//                , OffsetDateTime.now()
        );
    }
}
