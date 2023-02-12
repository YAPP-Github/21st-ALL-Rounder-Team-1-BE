package com.example.holaserver.Review;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Review.DTO.ReviewByStoreResponse;
import com.example.holaserver.Review.DTO.ReviewResponse;
import com.example.holaserver.Review.DTO.ReviewSaveBody;
import com.example.holaserver.Review.ImgReview.ImgReview;
import com.example.holaserver.Review.ImgReview.ImgReviewService;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLogService;
import com.example.holaserver.User.User;
import com.example.holaserver.User.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ImgReviewService imgReviewService;
    private final ReviewTagLogService reviewTagLogService;
    private final UserService userService;
    private final AuthService authService;

    @Transactional
    public Map<String, Object> saveReviewAndRelationInfo(ReviewSaveBody reviewSaveBody) {
        Long userId = authService.getPayloadByToken();
        ModelMap result = new ModelMap();
        Long reviewId = this.saveReview(reviewSaveBody);
        /* 이미지나 리뷰 태그를 달지 않을 수도 있어서 null 예외처리 X */
        List<Long> imgReviewIds = imgReviewService.saveImgReview(reviewId, reviewSaveBody.getImgPath());
        List<Long> reviewTagLogIds = reviewTagLogService.saveReviewTagLog(
                userId,
                reviewSaveBody.getStoreId(),
                reviewId,
                reviewSaveBody.getReviewTagIds()
        );
        userService.updateUserRating(userId, reviewRepository.findReviewsByUserId(authService.getPayloadByToken(),
                Sort.by(Sort.Order.desc("createdAt"))).size());

        result.addAttribute("reviewId", reviewId);
        result.addAttribute("imgReviewIds", imgReviewIds);
        result.addAttribute("reviewTagLogIds", reviewTagLogIds);
        return result;
    }

    public List<ReviewByStoreResponse> findReviewAndRelationInfo(Long storeId) throws Exception {
        authService.getPayloadByToken();
        List<Review> reviews = reviewRepository.findReviewsByStoreId(storeId, Sort.by(Sort.Order.desc("createdAt")));
        return reviews.stream().map(review -> new ReviewByStoreResponse(
                review,
                userService.findUserById(review.getUserId()),
                imgReviewService.findByReviewId(review.getId()),
                reviewTagLogService.findByReviewId(review.getId())
        )).collect(Collectors.toList());
    }


    private Long saveReview(ReviewSaveBody reviewSaveBody){
        return reviewRepository.save(reviewSaveBody.createReviewBuilder(authService.getPayloadByToken())).getId();
    }

    public ReviewResponse loadReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(NoSuchElementException::new);
        return new ReviewResponse(review);
    }

    public List<Review> loadReviewByUserId() {
        return reviewRepository.findReviewsByUserId(authService.getPayloadByToken(), Sort.by(Sort.Order.desc("createdAt")));
    }
}
