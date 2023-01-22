package com.example.holaserver.Review.ReviewTagLog;

import com.example.holaserver.Review.ReviewTagLog.ReviewTagLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTagLogRepository extends JpaRepository<ReviewTagLog, Long> {
}
