package com.example.holaserver.Item;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.Item.Enum.Unit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private String title;

    private Long price;

    private Unit unit;

    private String brand;

    private String category;

    private Timestamp removedAt;
}
