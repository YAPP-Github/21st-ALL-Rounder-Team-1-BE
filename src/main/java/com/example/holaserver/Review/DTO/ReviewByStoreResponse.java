package com.example.holaserver.Review.DTO;

import com.example.holaserver.Review.ImgReview.ImgReview;
import com.example.holaserver.Review.Review;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import com.example.holaserver.User.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewByStoreResponse {
    private final Long id;
    private final Long storeId;
    private final Long userId;
    private final String reviewText;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final User user;
    private final List<ImgReview> imgReviews;
    private final List<ReviewTagLog> reviewTagLogs;

    public ReviewByStoreResponse(Review review, User user, List<ImgReview> imgReviews, List<ReviewTagLog> reviewTagLogs) {
        this.id = review.getId();
        this.storeId = review.getStoreId();
        this.userId = review.getUserId();
        this.reviewText = review.getReviewText();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
        this.user = user;
        this.imgReviews = imgReviews;
        this.reviewTagLogs = reviewTagLogs;
    }

}
