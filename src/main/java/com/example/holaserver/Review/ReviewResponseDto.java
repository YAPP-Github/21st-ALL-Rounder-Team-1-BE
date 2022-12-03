package com.example.holaserver.Review;

import lombok.Getter;

@Getter
public class ReviewResponseDto {
    private Long userId;
    private Long storeId;
    private String reviewText;

    public ReviewResponseDto(Review entity){
        this.userId = entity.getUserId();
        this.storeId = entity.getStoreId();
        this.reviewText = entity.getReviewText();
    }
}
