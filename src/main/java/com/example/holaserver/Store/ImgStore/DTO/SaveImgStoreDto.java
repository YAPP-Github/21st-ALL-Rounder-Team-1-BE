package com.example.holaserver.Store.ImgStore.DTO;

import com.example.holaserver.Store.ImgStore.ImgStore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveImgStoreDto {
    private Long storeId;
    private String path;
    private Date removed_at;

    @Builder
    public ImgStore toEntity() {
        return ImgStore.builder()
                .storeId(storeId)
                .path(path)
                .build();
    }
}
