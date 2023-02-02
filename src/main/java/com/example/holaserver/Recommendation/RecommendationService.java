package com.example.holaserver.Recommendation;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Recommendation.DTO.RecommendationBody;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

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

    public Map<String, Object> findRecommendation(RecommendationBody recommendationBody) {
        ModelMap result = new ModelMap();



//        result.addAttribute("recommendation", isRecommendation);
//        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    @Transactional
    public Map<String, Object> saveRecommendation(RecommendationBody recommendationBody) {
        ModelMap result = new ModelMap();
        // pre-check 필요
        if (recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "유저가 이미 추천한 가게", new BadHttpRequest());
        Long recommendationId = saveRecommendationByStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId());
        Boolean isRecommendation = recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId());
        Long storeRecommendationsCount = (long) findRecommendationByStoreId(recommendationBody.getStoreId()).size();

        result.addAttribute("recommendationId", recommendationId);
        result.addAttribute("isRecommendation", isRecommendation);
        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    @Transactional
    public Map<String, Object> removeRecommendation(RecommendationBody recommendationBody) throws NotFoundException {
        ModelMap result = new ModelMap();
        Recommendation recommendation = findRecommendationByUserIdAndStoreId(
                authService.getPayloadByToken(), recommendationBody.getStoreId());
        recommendation.removeRecommendation();
        Long storeRecommendationsCount = (long) findRecommendationByStoreId(recommendationBody.getStoreId()).size();
        Boolean isRecommendation = recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId());
        result.addAttribute("isRecommendation", isRecommendation);
        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    private Long saveRecommendationByStoreId(Long userId, Long storeId) {
        return recommendationRepository.save(new RecommendationBody().createSaveRecommendationBuilder(userId, storeId)).getId();
    }

    private Recommendation findRecommendationByUserIdAndStoreId(Long userId, Long storeId) {
        return recommendationRepository.findRecommendationByUserIdAndStoreId(userId, storeId).orElseThrow(NoSuchElementException::new);
    }

    private List<Recommendation> findRecommendationByStoreId(Long storeId) {
        return recommendationRepository.findRecommendationsByStoreId(storeId);
    }

}
