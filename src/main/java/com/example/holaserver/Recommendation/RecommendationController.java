package com.example.holaserver.Recommendation;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Recommendation.DTO.RecommendationBody;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping("/recommendation")
    public ResponseTemplate<Map<String, Object>> recommendationSave(@RequestBody RecommendationBody recommendationBody) {
        return new ResponseTemplate<>(recommendationService.saveRecommendation(recommendationBody), "추천하기 성공");
    }

    @DeleteMapping("/recommendation")
    public ResponseTemplate<Map<String, Object>> recommendationDelete(@RequestBody RecommendationBody recommendationBody) throws NotFoundException {
        return new ResponseTemplate<>(recommendationService.removeRecommendation(recommendationBody), "추천하기 취소 성공");
    }
}
