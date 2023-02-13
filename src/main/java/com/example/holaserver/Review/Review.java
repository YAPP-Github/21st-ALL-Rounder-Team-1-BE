package com.example.holaserver.Review;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@Where(clause = "removed_at IS NULL")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private Long userId;

    private String reviewText;
    @Setter
    private Timestamp removedAt;
}
