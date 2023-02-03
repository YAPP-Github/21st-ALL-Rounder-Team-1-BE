package com.example.holaserver.Item.DTO;

import com.example.holaserver.Item.Enum.Unit;
import com.example.holaserver.Item.Item;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemSaveBody {
    private Long id;
    private String title;
    private Long price;
    private Unit unit;
    private String brand;
    private String category;
    private String imgPath;
    private Boolean isReady;

    public Item createSaveItemBuilder(Long storeId, Boolean isReady) {
        return Item.builder()
                .storeId(storeId)
                .title(title)
                .price(price)
                .unit(unit)
                .brand(brand)
                .category(category)
                .imgPath(imgPath)
                .isReady(isReady)
                .build();
    }

    public Item updateSaveItemBuilder(Long storeId, Boolean isReady, Long itemId) {
        return Item.builder()
                .id(itemId)
                .storeId(storeId)
                .title(title)
                .price(price)
                .unit(unit)
                .brand(brand)
                .category(category)
                .imgPath(imgPath)
                .isReady(isReady)
                .build();
    }
}
