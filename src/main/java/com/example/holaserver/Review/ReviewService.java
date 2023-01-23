package com.example.holaserver.Review;

import com.example.holaserver.Review.DTO.ReviewResponse;
import com.example.holaserver.Review.DTO.ReviewSaveParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Long saveReview(ReviewSaveParameter requestDto){
        Review review = Review.builder()
                .storeId(requestDto.getStoreId())
                .userId(requestDto.getUserId())
                .reviewText(requestDto.getReviewText())
                .build();
        return reviewRepository.save(review).getId();
    }

    public ReviewResponse loadReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException());
        return new ReviewResponse(review);
    }
}
