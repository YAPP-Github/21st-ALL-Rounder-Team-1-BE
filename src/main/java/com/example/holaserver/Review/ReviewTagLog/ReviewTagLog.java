package com.example.holaserver.Review.ReviewTagLog;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class ReviewTagLog extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reviewId;

    private Long userId;

    private Long storeId;

    private Long reviewTagId;

    private Timestamp removedAt;
}
