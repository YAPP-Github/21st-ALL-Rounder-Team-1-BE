package com.example.holaserver.Review.DTO;

import com.example.holaserver.Review.ImgReview.ImgReview;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import com.example.holaserver.User.User;
import lombok.Getter;

@Getter
public class ReviewByStoreResponse {
    private Long id;
    private Long storeId;
    private Long userId;
    private String reviewText;
    private String createdAt;
    private String modifiedAt;
    private User user;
    private ImgReview[] imgReviews;
    private ReviewTagLog[] reviewTagLogs;

}
