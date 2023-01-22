package com.example.holaserver.Review;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Review.DTO.ReviewResponseDto;
import com.example.holaserver.Review.DTO.ReviewSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseTemplate<Long> saveReview(@RequestBody ReviewSaveRequestDto requestDto){
        return new ResponseTemplate<>(reviewService.saveReview(requestDto), "리뷰 저장에 성공했습니다.");
    }

    @GetMapping("/review/{reviewId}")
    public ResponseTemplate<ReviewResponseDto> loadReview(@PathVariable Long reviewId){
        return new ResponseTemplate<>(reviewService.loadReview(reviewId), "리뷰 불러오기에 성공했습니다.");
    }
}
