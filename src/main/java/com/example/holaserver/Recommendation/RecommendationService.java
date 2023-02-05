package com.example.holaserver.Recommendation;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Recommendation.DTO.RecommendationBody;
import com.example.holaserver.Store.StoreService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final StoreService storeService;
    private final AuthService authService;

    public Map<String, Object> findRecommendation(Long storeId) {
        ModelMap result = new ModelMap();

        Boolean isRecommendation = recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), storeId);
        Long storeRecommendationsCount = (long) findRecommendationByStoreId(storeId).size();

        result.addAttribute("recommendation", isRecommendation);
        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    // TODO: Transaction Isolation Level 추가필요
    @Transactional
    public Map<String, Object> saveRecommendation(RecommendationBody recommendationBody) throws Exception {
        ModelMap result = new ModelMap();
        // pre-check 필요
        if (!storeService.existStoreById(recommendationBody.getStoreId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        if (recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 추천한 가게입니다 \uD83D\uDE1C");
        }
        Long recommendationId = saveRecommendationByStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId());
        Boolean isRecommendation = recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId());
        Long storeRecommendationsCount = (long) findRecommendationByStoreId(recommendationBody.getStoreId()).size();

        result.addAttribute("recommendationId", recommendationId);
        result.addAttribute("isRecommendation", isRecommendation);
        result.addAttribute("count", storeRecommendationsCount);
        return result;
    }

    @Transactional
    public Map<String, Object> removeRecommendation(RecommendationBody recommendationBody) {
        ModelMap result = new ModelMap();
        if (!storeService.existStoreById(recommendationBody.getStoreId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        if (!recommendationRepository.existsByUserIdAndStoreId(authService.getPayloadByToken(), recommendationBody.getStoreId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 완료된 요청입니다 \uD83D\uDE09 ");
        }
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
