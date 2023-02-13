package com.example.holaserver.Recommendation;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Where(clause = "removed_at IS NULL")
@Table(name = "recommendation")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Recommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long storeId;
    @Setter
    private Timestamp removedAt;

    @Builder
    public Recommendation(Long userId, Long storeId) {
        this.userId = userId;
        this.storeId = storeId;
    }

    public void removeRecommendation() {
        this.removedAt = new Timestamp(System.currentTimeMillis());
    }
}
