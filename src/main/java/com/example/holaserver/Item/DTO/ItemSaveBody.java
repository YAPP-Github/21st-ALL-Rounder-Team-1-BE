package com.example.holaserver.Item.DTO;

import com.example.holaserver.Item.Enum.Unit;
import com.example.holaserver.Item.Item;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ItemSaveBody {
    private String title;
    private Long price;
    private Unit unit;
    private String brand;
    private String category;

    public Item createSaveItemBuilder(Long storeId) {
        return Item.builder()
                .storeId(storeId)
                .title(title)
                .price(price)
                .unit(unit)
                .brand(brand)
                .category(category)
                .build();
    }
}
