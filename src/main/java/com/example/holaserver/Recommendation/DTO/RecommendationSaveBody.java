package com.example.holaserver.Recommendation.DTO;

import com.example.holaserver.Recommendation.Recommendation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RecommendationSaveBody {
    private Long storeId;

    public Recommendation createSaveRecommendationBuilder(Long userId, Long storeId) {
        return Recommendation.builder()
                .userId(userId)
                .storeId(storeId)
                .build();
    }
}
