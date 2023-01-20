package com.example.holaserver.Item.DTO;

import com.example.holaserver.Item.Enum.Unit;
import com.example.holaserver.Item.Item;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SaveItemDto {
    private Long storeId;
    private String title;
    private Long price;
    private Unit unit;
    private String brand;
    private String category;
}
