package com.example.holaserver.Review.DTO;

import com.example.holaserver.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSaveParameter {
    private Long userId;
    private Long storeId;
    private String reviewText;

    public Review createSaveStoreBuilder(){
        return Review.builder()
                .storeId(storeId)
                .userId(userId)
                .reviewText(reviewText)
                .build();
    }
}
