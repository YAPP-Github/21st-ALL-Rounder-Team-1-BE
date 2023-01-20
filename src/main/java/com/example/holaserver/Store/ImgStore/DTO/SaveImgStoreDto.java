package com.example.holaserver.Store.ImgStore.DTO;

import com.example.holaserver.Store.ImgStore.ImgStore;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveImgStoreDto {
    private Long storeId;
    private String path;

    @Builder
    public ImgStore toEntity() {
        return ImgStore.builder()
                .storeId(storeId)
                .path(path)
                .build();
    }
}
