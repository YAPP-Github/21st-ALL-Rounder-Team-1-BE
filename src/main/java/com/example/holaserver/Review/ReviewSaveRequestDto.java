package com.example.holaserver.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private Long userId;
    private Long storeId;
    private String reviewText;

    @Builder
    public Review toEntity(){
        return Review.builder()
                .storeId(storeId)
                .userId(userId)
                .reviewText(reviewText)
                .build();
    }
}
