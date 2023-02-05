package com.example.holaserver.Review.ImgReview;

import com.example.holaserver.Review.ImgReview.DTO.ImgReviewBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImgReviewService {
    private final ImgReviewRepository imgReviewRepository;

    public List<Long> saveImgReview(Long reviewId, String[] pathDatas) {
        return Arrays.stream(pathDatas).map(pathData -> {
            ImgReview imgReview = new ImgReviewBody().createSaveImgReviewBuilder(reviewId, pathData);
            return this.imgReviewRepository.save(imgReview).getId();
        }).collect(Collectors.toList());
    }

    public List<ImgReview> findByReviewId(Long reviewId) {
        return imgReviewRepository.findImgReviewsByReviewId(reviewId);
    }
}
