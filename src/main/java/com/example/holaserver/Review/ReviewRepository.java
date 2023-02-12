package com.example.holaserver.Review;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByStoreId(Long storeId, Sort sort);
    List<Review> findReviewsByUserId(Long userId, Sort sort);
}
