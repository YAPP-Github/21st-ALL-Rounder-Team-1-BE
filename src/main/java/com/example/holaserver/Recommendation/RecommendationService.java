package com.example.holaserver.Recommendation;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Recommendation.DTO.RecommendationSaveBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private RecommendationRepository recommendationRepository;
    private AuthService authService;

    public Map<String, Object> saveRecommendation(RecommendationSaveBody recommendationSaveBody) {
        ModelMap result = new ModelMap();
        Long recommendationId = saveRecommendationByStoreId(authService.getPayloadByToken(), recommendationSaveBody.getStoreId());
        Optional<Recommendation> isRecommendation = findRecommendationByUserIdAndStoreId(
                authService.getPayloadByToken(), recommendationSaveBody.getStoreId());

        Long storeRecommendationsCount = (long) findRecommendationByStoreId(recommendationSaveBody.getStoreId()).size();

        result.addAttribute("recommendationId", recommendationId);
        result.addAttribute("recommendation", isRecommendation.isPresent());
        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    private Long saveRecommendationByStoreId(Long userId, Long storeId) {
        return recommendationRepository.save(new RecommendationSaveBody().createSaveRecommendationBuilder(userId, storeId)).getId();
    }

    private Optional<Recommendation> findRecommendationByUserIdAndStoreId(Long userId, Long storeId) {
        return recommendationRepository.findRecommendationByUserIdAndStoreId(userId, storeId);
    }

    private List<Recommendation> findRecommendationByStoreId(Long storeId) {
        return recommendationRepository.findRecommendationsByStoreId(storeId);
    }

}
