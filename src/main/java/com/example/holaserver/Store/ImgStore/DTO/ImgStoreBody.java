package com.example.holaserver.Store.ImgStore.DTO;

import com.example.holaserver.Store.ImgStore.ImgStore;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ImgStoreBody {
    private Long storeId;
    private String path;

    @Builder
    public ImgStore createSaveImgStoreBuilder(Long storeId, String path) {
        return ImgStore.builder()
                .storeId(storeId)
                .path(path)
                .build();
    }
}
