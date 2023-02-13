package com.example.holaserver.Review.ImgReview;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Where(clause = "removed_at IS NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImgReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reviewId;
    private String path;
    @Setter
    private Timestamp removedAt;

    @Builder
    public ImgReview(Long reviewId, String path) {
        this.reviewId = reviewId;
        this.path = path;
    }
}
