package com.example.holaserver.CustomerSatisfaction;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Getter
@Where(clause = "removed_at IS NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "customer_satisfaction")
public class CustomerSatisfaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "type")
    private Integer type;
    @Column(name = "content")
    private String content;
    private Timestamp removedAt;

    @Builder
    public CustomerSatisfaction(Long userId, Integer type, String content) {
        this.userId = userId;
        this.type = type;
        this.content = content;
    }
}

