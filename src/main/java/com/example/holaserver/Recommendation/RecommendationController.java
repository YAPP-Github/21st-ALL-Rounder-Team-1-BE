package com.example.holaserver.Recommendation;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Recommendation.DTO.RecommendationSaveBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping("/recommendation")
    public ResponseTemplate<Map<String, Object>> recommendationSave(@RequestBody RecommendationSaveBody) {
        return new ResponseTemplate<>(recommendationService.)
    }
}
