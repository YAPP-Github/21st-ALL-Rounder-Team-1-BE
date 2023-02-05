package com.example.holaserver.Review.ReviewTagLog.DTO;

import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReviewTagLogBody {
    private Long userId;
    private Long storeId;
    private Long reviewId;
    private Long[] reviewTagLogIds;

    @Builder
    public ReviewTagLog createSaveReviewTagLogBuilder(
            Long userId, Long storeId, Long reviewId, Long reviewTagId
        ) {
        return ReviewTagLog.builder()
                .userId(userId)
                .storeId(storeId)
                .reviewId(reviewId)
                .reviewTagId(reviewTagId)
                .build();
    }
}
