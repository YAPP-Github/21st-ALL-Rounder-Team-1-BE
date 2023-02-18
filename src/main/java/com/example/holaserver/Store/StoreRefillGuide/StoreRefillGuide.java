package com.example.holaserver.Store.StoreRefillGuide;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name="store_refill_guide")
@Where(clause = "removed_at Is NULL")
@NoArgsConstructor
public class StoreRefillGuide extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storeId;
    private String imgPath;
    private Timestamp removedAt;
}
