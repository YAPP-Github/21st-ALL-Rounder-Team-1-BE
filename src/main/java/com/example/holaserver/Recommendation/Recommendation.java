package com.example.holaserver.Recommendation;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.Review.ImgReview.ImgReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Where(clause = "removed_at IS NULL")
@Table(name = "recommendation")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Recommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long storeId;
    private Timestamp removedAt;

    @Builder
    public Recommendation(Long userId, Long storeId) {
        this.userId = userId;
        this.storeId = storeId;
    }

    public void removedRecommendation() {
        this.removedAt = new Timestamp(System.currentTimeMillis());
    }
}
