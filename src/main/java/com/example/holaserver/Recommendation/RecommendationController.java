package com.example.holaserver.Recommendation;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Recommendation.DTO.RecommendationBody;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/{storeId}/recommendation")
    public ResponseTemplate<Map<String, Object>> recommendationList(@PathVariable Long storeId) {
        return new ResponseTemplate<>(recommendationService.findRecommendation(storeId), "추천 불러오기 성공");
    }

    @PostMapping("/recommendation")
    public ResponseTemplate<Map<String, Object>> recommendationSave(@RequestBody RecommendationBody recommendationBody) throws Exception {
        return new ResponseTemplate<>(recommendationService.saveRecommendation(recommendationBody), "추천하기 성공");
    }

    @DeleteMapping("/recommendation")
    public ResponseTemplate<Map<String, Object>> recommendationDelete(@RequestBody RecommendationBody recommendationBody) throws NotFoundException {
        return new ResponseTemplate<>(recommendationService.removeRecommendation(recommendationBody), "추천하기 취소 성공");
    }
}
