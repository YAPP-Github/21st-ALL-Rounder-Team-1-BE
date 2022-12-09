package com.example.holaserver.review;

import com.example.holaserver.Review.Review;
import com.example.holaserver.Review.ReviewRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewRepository reviewRepository;

    @After
    public void tearDown() throws Exception {
        reviewRepository.deleteAll();
    }

    @Test
    public void Review_등록된다() throws Exception {
        //given
        Long storeId = 9L;
        Long userId = 5L;
        String reviewText = "테스트 리뷰입니다.";
        Review review = Review.builder()
                .storeId(storeId)
                .userId(userId)
                .reviewText(reviewText)
                .build();

        String url = "http://localhost"+port+"/review";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, review, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Review> all = reviewRepository.findAll();
        assertThat(all.get(0).getStoreId()).isEqualTo(storeId);
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getReviewText()).isEqualTo(reviewText);
    }
}
