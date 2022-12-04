package com.example.holaserver.Review;

import lombok.Getter;

@Getter
public class ReviewResponseDto {
    private final Long userId;
    private final Long storeId;
    private final String reviewText;

    public ReviewResponseDto(Review entity){
        this.userId = entity.getUserId();
        this.storeId = entity.getStoreId();
        this.reviewText = entity.getReviewText();
    }
}
