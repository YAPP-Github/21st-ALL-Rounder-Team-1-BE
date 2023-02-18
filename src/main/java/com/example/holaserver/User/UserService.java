package com.example.holaserver.User;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Auth.JwtTokenProvider;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.Recommendation.Recommendation;
import com.example.holaserver.Recommendation.RecommendationRepository;
import com.example.holaserver.Review.ImgReview.ImgReview;
import com.example.holaserver.Review.ImgReview.ImgReviewRepository;
import com.example.holaserver.Review.Review;
import com.example.holaserver.Review.ReviewRepository;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import com.example.holaserver.Review.ReviewTagLog.ReviewTagLogRepository;
import com.example.holaserver.Store.StoreRepository;
import com.example.holaserver.User.Dto.BossSaveBody;
import com.example.holaserver.User.Dto.UserDeleteResponse;
import com.example.holaserver.User.Dto.UserInfoResponse;
import com.example.holaserver.User.Dto.UserSaveBody;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.*;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewTagLogRepository reviewTagLogRepository;
    private final ImgReviewRepository imgReviewRepository;
    private final RecommendationRepository recommendationRepository;

    private final OauthService oauthService;
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final Long TEST_ACCOUNT_PK = 83L;

    public String saveUser(UserSaveBody userSaveBody) {
        if (userRepository.findByOauthIdentity(userSaveBody.getOauthIdentity()) != null) {
            Long userId = userRepository.findByOauthIdentity(userSaveBody.getOauthIdentity()).getId();
            return jwtTokenProvider.createToken(userId);
        }
        User user = User.builder()
                .name(userSaveBody.getName())
                .nickname(randomNickname())
                .email(userSaveBody.getEmail())
                .rating((byte) 1)
                .imgPath(userSaveBody.getImgPath())
                .type(userSaveBody.getType())
                .oauthIdentity(userSaveBody.getOauthIdentity())
                .oauthType(userSaveBody.getOauthType())
                .build();
        return jwtTokenProvider.createToken(userRepository.save(user).getId());
    }

    public UserInfoResponse loadMyInfo() throws NotFoundException {
        Long userId = authService.getPayloadByToken();
        if (userId == null) throw new NotFoundException("올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return UserInfoResponse.builder()
                .entity(user)
                .build();
    }

    public Boolean findDuplicated(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public User updateBoss(BossSaveBody bossSaveBody) {
        Long userId = authService.getPayloadByToken();
        if (userId == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 유저의 가게가 없습니다."));
        user.saveBoss(bossSaveBody.getName(), bossSaveBody.getEmail(), bossSaveBody.getPhoneNumber());
        return user;
    }

    public void updateUserRating(Long userId, int reviewSize) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.modifyUserRating(reviewSize >= 5 ? 4 : reviewSize >= 3 ? 3 : reviewSize >= 1 ? 2 : 0);
    }

    public User modifyUser(User userModifyBody) throws NotFoundException {
        Long userId = authService.getPayloadByToken();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원 정보입니다 \uD83E\uDDD0"));
        user.modifyUser(userModifyBody);
        return user;
    }

    public Optional<User> findUser() {
        return userRepository.findById(authService.getPayloadByToken());
    }

    public Long removeUser() throws NotFoundException {
        Long userId = authService.getPayloadByToken();
        if (Objects.equals(userId, TEST_ACCOUNT_PK))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "둘러보기 계정은 삭제할 수 없습니다.");
        if (userId == null) throw new NotFoundException("올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.removeUser();
        List<Review> reviews = reviewRepository.findReviewsByUserId(user.getId(), Sort.by(Sort.Order.desc("createdAt")));
        for (Review review : reviews) {
            List<ReviewTagLog> tag = reviewTagLogRepository.findReviewTagLogByReviewId(review.getId());
            for (ReviewTagLog reviewTagLog : tag) reviewTagLog.setRemovedAt(new Timestamp(System.currentTimeMillis()));
            List<ImgReview> imgReviews = imgReviewRepository.findImgReviewsByReviewId(review.getId());
            for (ImgReview imgReview : imgReviews) imgReview.setRemovedAt(new Timestamp(System.currentTimeMillis()));
            review.setRemovedAt(new Timestamp(System.currentTimeMillis()));
        }
        List<Recommendation> recommendations = recommendationRepository.findAllByUserId(user.getId());
        for (Recommendation recommendation : recommendations)
            recommendation.setRemovedAt(new Timestamp(System.currentTimeMillis()));
        return userId;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    public String randomNickname() {
        String result;
        List<String> ad = Arrays.asList("리필하는", "다시 채우는", "재활용하는", "환경을 위하는", "쓰레기를 줄이는", "텀블러를 사용하는", "지구를 위하는", "플로깅하는", "전기를 절약하는", "자연 친화적인", "환경을 보호하는", "새활용하는");
        List<String> animal = Arrays.asList("반달가슴곰", "산양", "노랑목도리담비", "스라소니", "수달", "표범", "시베리아 호랑이", "늑대", "사막여우", "쿠바악어", "검은코뿔소", "붉은머리독수리", "오랑우탄", "큰귀상어", "골리앗개구리", "긴팔원숭이", "노란눈펭귄", "두루미", "따오기", "마운틴고릴라", "승냥이", "붉바리", "인도강돌고래", "황새", "큰갑옷도마뱀", "듀공", "바다이구아나", "백상아리", "북극곰", "인도코뿔소", "향유고래", "흰올빼미", "뱀상어", "재규어", "모래고양이", "히말라야독수리");
        Random rand = new Random();
        int adIndex = rand.nextInt(ad.size());
        int animalIndex = rand.nextInt(animal.size());

        result = ad.get(adIndex) + " " + animal.get(animalIndex);
        int count = userRepository.countUserByNicknameContaining(result);
        result += String.format("%02d", count + 1);
        return result;
    }

    public Map<String, Object> createTestAccountToken() {
        ModelMap result = new ModelMap();
        return result.addAttribute("jwt", jwtTokenProvider.createAccessToken(this.TEST_ACCOUNT_PK));
    }
}
