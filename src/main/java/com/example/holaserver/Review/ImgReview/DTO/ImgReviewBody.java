package com.example.holaserver.Review.ImgReview.DTO;

import com.example.holaserver.Review.ImgReview.ImgReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ImgReviewBody {
    private Long reviewId;
    private String path;

    @Builder
    public ImgReview createSaveImgReviewBuilder(Long reviewId, String path) {
        return ImgReview.builder()
                .reviewId(reviewId)
                .path(path)
                .build();
    }
}
