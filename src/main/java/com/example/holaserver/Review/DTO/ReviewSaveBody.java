package com.example.holaserver.Review.DTO;

import com.example.holaserver.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSaveBody {
    private Long userId;
    private Long storeId;
    private String reviewText;
    private String[] imgPath;
    private Long[] reviewTagIds;

    public Review createReviewBuilder(){
        return Review.builder()
                .storeId(storeId)
                .userId(userId)
                .reviewText(reviewText)
                .build();
    }
}
