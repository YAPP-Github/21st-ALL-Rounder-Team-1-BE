package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Store.ImgStore.DTO.ImgStoreBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImgStoreService {
    private final ImgStoreRepository imgStoreRepository;

    public List<Long> saveImgStores(Long storeId, String[] pathDatas) {
        return Arrays.stream(pathDatas).map(pathData -> {
            ImgStore imgStore = new ImgStoreBody().createSaveImgStoreBuilder(storeId, pathData);
            return this.imgStoreRepository.save(imgStore).getId();
        }).collect(Collectors.toList());
    }

    public List<ImgStore> findImgStoreByStoreId(Long storeId) {
        return imgStoreRepository.findImgStoreByStoreId(storeId);
    }

    public void deleteByStoreId(Long storeId) {
        imgStoreRepository.deleteImgStoresByStoreId(storeId);
    }

}
