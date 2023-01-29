package com.example.holaserver.Review;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Review.DTO.ReviewByStoreResponse;
import com.example.holaserver.Review.DTO.ReviewResponse;
import com.example.holaserver.Review.DTO.ReviewSaveBody;
import com.example.holaserver.Review.ImgReview.ImgReviewService;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLogService;
import com.example.holaserver.User.User;
import com.example.holaserver.User.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ImgReviewService imgReviewService;
    private final ReviewTagLogService reviewTagLogService;
    private final UserService userService;

    @Transactional
    public Map<String, Object> saveReviewAndRelationInfo(ReviewSaveBody reviewSaveBody) {
        ModelMap result = new ModelMap();
        Long reviewId = this.saveReview(reviewSaveBody);
        /* 이미지나 리뷰 태그를 달지 않을 수도 있어서 null 예외처리 X */
        List<Long> imgReviewIds = imgReviewService.saveImgReview(reviewId, reviewSaveBody.getImgPath());
        List<Long> reviewTagLogIds = reviewTagLogService.saveReviewTagLog(
                reviewSaveBody.getUserId(),
                reviewSaveBody.getStoreId(),
                reviewId,
                reviewSaveBody.getReviewTagIds()
        );
        result.addAttribute("reviewId", reviewId);
        result.addAttribute("imgReviewIds", imgReviewIds);
        result.addAttribute("reviewTagLogIds", reviewTagLogIds);
        return result;
    }

    public List<ReviewByStoreResponse> findReviewAndRelationInfo(Long storeId) throws Exception {
        Optional<User> user = Optional.ofNullable(userService.findUser().orElseThrow(() -> new NotFoundException("유저 정보가 없습니다.")));
        List<Review> reviews = reviewRepository.findReviewsByStoreId(storeId);


    }


    private Long saveReview(ReviewSaveBody reviewSaveBody){
        return reviewRepository.save(reviewSaveBody.createReviewBuilder()).getId();
    }

    private List<Review> findReviewByStoreId(Long storeId) {
        return reviewRepository.findReviewsByStoreId(storeId);
    }

    public ReviewResponse loadReview(Long reviewId){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(NoSuchElementException::new);
        return new ReviewResponse(review);
    }
}
