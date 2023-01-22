package com.example.holaserver.Store.ImgStore.DTO;

import com.example.holaserver.Store.ImgStore.ImgStore;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImgStoreBody {
    private Long storeId;
    private String path;

    public ImgStore createSaveImgStoreBuilder() {
        return ImgStore.builder()
                .storeId(storeId)
                .path(path)
                .build();
    }
}
