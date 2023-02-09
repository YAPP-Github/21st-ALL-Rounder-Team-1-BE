package com.example.holaserver.Item;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.Item.Enum.Unit;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "removed_at IS NULL")
@Builder
@Getter
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storeId;
    private String title;
    private Long price;
    @Enumerated(EnumType.STRING)
    private Unit unit;
    private String brand;
    private String category;
    private String imgPath;
    private Boolean isHided;
    private Boolean isReady;
}
