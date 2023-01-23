package com.example.holaserver.Item;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.Item.Enum.Unit;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    private Timestamp removedAt;
}
