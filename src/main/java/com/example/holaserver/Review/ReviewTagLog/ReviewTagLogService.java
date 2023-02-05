package com.example.holaserver.Review.ReviewTagLog;

import com.example.holaserver.Review.ReviewTagLog.DTO.ReviewTagLogBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewTagLogService {
    private final ReviewTagLogRepository reviewTagLogRepository;

    public List<Long> saveReviewTagLog(Long userId, Long storeId, Long reviewId, Long[] reviewTagIds) {
        return Arrays.stream(reviewTagIds).map(reviewTagId -> {
            ReviewTagLog reviewTagLog = new ReviewTagLogBody()
                    .createSaveReviewTagLogBuilder(userId, storeId, reviewId, reviewTagId);
            return this.reviewTagLogRepository.save(reviewTagLog).getId();
        }).collect(Collectors.toList());
    }

    public List<ReviewTagLog> findByReviewId(Long reviewId) {
        return reviewTagLogRepository.findReviewTagLogByReviewId(reviewId);
    }
}
