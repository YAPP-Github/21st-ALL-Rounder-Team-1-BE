package com.example.holaserver.Review;

import com.example.holaserver.Review.DTO.ReviewResponse;
import com.example.holaserver.Review.DTO.ReviewSaveBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Map<String, Object> saveReviewAndRelationInfo(ReviewSaveBody reviewSaveBody) {
        ModelMap result = new ModelMap();
        Long reviewId = this.saveReview(reviewSaveBody);

        return result;
    }

    private Long saveReview(ReviewSaveBody reviewSaveBody){
        return reviewRepository.save(reviewSaveBody.createReviewBuilder()).getId();
    }

    public ReviewResponse loadReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException());
        return new ReviewResponse(review);
    }
}
