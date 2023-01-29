package com.example.holaserver.Review.ImgReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgReviewRepository extends JpaRepository<ImgReview, Long> {
    List<ImgReview> findImgReviewsByReviewId(Long reviewId);
}
