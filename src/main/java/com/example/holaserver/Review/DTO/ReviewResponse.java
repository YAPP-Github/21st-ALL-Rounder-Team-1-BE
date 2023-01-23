package com.example.holaserver.Review.DTO;

import com.example.holaserver.Review.Review;
import lombok.Getter;

@Getter
public class ReviewResponse {
    private final Long userId;
    private final Long storeId;
    private final String reviewText;

    public ReviewResponse(Review entity){
        this.userId = entity.getUserId();
        this.storeId = entity.getStoreId();
        this.reviewText = entity.getReviewText();
    }
}
