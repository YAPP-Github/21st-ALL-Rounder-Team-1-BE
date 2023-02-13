package com.example.holaserver.Review.ReviewTagLog;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewTagLog extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reviewId;

    private Long userId;

    private Long storeId;

    private Long reviewTagId;

    @Setter
    private Timestamp removedAt;
}
