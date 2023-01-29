package com.example.holaserver.Review.ReviewTagLog;

import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewTagLogRepository extends JpaRepository<ReviewTagLog, Long> {
    List<ReviewTagLog> findReviewTagLogByReviewId(Long reviewId);
}
