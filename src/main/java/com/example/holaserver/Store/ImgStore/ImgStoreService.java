package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Store.ImgStore.DTO.ImgStoreBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImgStoreService {
    private final ImgStoreRepository imgStoreRepository;

    public List<Long> saveImgStores(Long storeId, String[] pathDatas) {
//        List<Long> imgStoreIds = new ArrayList<Long>();
//        pathDatas = pathDatas.replaceAll("[\\[\\[\\]]","");
//        if (pathDatas.equals("")) return imgStoreIds;
//        for (String path : pathDatas.split(",")) {
//            ImgStore imgStore = new ImgStoreBody(storeId, path).createSaveImgStoreBuilder();
//            imgStoreIds.add(this.imgStoreRepository.save(imgStore).getId());
//        }
        return Arrays.stream(pathDatas).map(pathData -> {
            ImgStore imgStore = new ImgStoreBody(storeId, pathData).createSaveImgStoreBuilder();
            return this.imgStoreRepository.save(imgStore).getId();
        }).collect(Collectors.toList());
    }

    public List<ImgStore> findImgStoreByStoreId(Long storeId) {
        return imgStoreRepository.findImgStoreByStoreId(storeId);
    }
}
