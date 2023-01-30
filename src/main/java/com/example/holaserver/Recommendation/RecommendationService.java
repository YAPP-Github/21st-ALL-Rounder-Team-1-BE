package com.example.holaserver.Recommendation;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Recommendation.DTO.RecommendationBody;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final AuthService authService;

    @Transactional
    public Map<String, Object> saveRecommendation(RecommendationBody recommendationBody) {
        ModelMap result = new ModelMap();
        Long recommendationId = saveRecommendationByStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId());
        Recommendation recommendation = findRecommendationByUserIdAndStoreId(
                authService.getPayloadByToken(), recommendationBody.getStoreId());

        Long storeRecommendationsCount = (long) findRecommendationByStoreId(recommendationBody.getStoreId()).size();

        result.addAttribute("recommendationId", recommendationId);
        result.addAttribute("recommendation", recommendation);
        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    public Map<String, Object> removeRecommendation(RecommendationBody recommendationBody) throws NotFoundException {
        ModelMap result = new ModelMap();
        Recommendation recommendation = findRecommendationByUserIdAndStoreId(
                authService.getPayloadByToken(), recommendationBody.getStoreId());
        recommendation.removedRecommendation();
        return result;
    }

    private Long saveRecommendationByStoreId(Long userId, Long storeId) {
        return recommendationRepository.save(new RecommendationBody().createSaveRecommendationBuilder(userId, storeId)).getId();
    }

    private Recommendation findRecommendationByUserIdAndStoreId(Long userId, Long storeId) {
        return recommendationRepository.findRecommendationByUserIdAndStoreId(userId, storeId);
    }

    private List<Recommendation> findRecommendationByStoreId(Long storeId) {
        return recommendationRepository.findRecommendationsByStoreId(storeId);
    }

}
