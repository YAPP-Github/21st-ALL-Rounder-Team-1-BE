package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Store.ImgStore.DTO.ImgStoreParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImgStoreService {
    private final ImgStoreRepository imgStoreRepository;

    public List<Long> saveImgStores(Long storeId, String pathDatas) {
        List<Long> imgStoreIds = new ArrayList<Long>();
        pathDatas = pathDatas.replace("[", "");
        pathDatas = pathDatas.replace("]", "");
        if (pathDatas.equals("")) return imgStoreIds;
        for (String path : pathDatas.split(",")) {
            ImgStore imgStore = new ImgStoreParameter(storeId, path).createSaveImgStoreBuilder();
            imgStoreIds.add(this.imgStoreRepository.save(imgStore).getId());
        }
        return imgStoreIds;
    }
}
