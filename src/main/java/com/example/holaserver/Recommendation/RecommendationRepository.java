package com.example.holaserver.Recommendation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Recommendation findRecommendationByUserIdAndStoreId(Long userId, Long storeId);
    List<Recommendation> findRecommendationsByStoreId(Long storeId);

}
