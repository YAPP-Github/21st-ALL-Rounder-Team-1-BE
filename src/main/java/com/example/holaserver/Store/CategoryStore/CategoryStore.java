package com.example.holaserver.Store.CategoryStore;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Where(clause = "removed_at IS NULL")
@NoArgsConstructor
public class CategoryStore extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private String categoryText;

    private Timestamp removedAt;
}
