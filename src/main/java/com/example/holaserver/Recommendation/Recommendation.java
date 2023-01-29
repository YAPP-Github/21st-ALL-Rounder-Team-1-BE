package com.example.holaserver.Recommendation;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.Review.ImgReview.ImgReview;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Recommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long storeId;

    @Builder
    public Recommendation(Long userId, Long storeId) {
        this.userId = userId;
        this.storeId = storeId;
    }
}
