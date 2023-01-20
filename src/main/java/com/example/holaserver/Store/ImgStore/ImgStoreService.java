package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Store.ImgStore.DTO.SaveImgStoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImgStoreService {
    private final ImgStoreRepository imgStoreRepository;

    public List<Long> saveImgStore(Long storeId, String pathDatas) {
        List<Long> imgStoreIds = new ArrayList<Long>();
        pathDatas = pathDatas.replace("[", "");
        pathDatas = pathDatas.replace("]", "");
        if (pathDatas.equals("")) return imgStoreIds;
        for (String path : pathDatas.split(",")) {
//            ImgStore imgStore = this.createSaveImgStoreBuilder(new SaveImgStoreDto(storeId, path));
            ImgStore imgStore = new SaveImgStoreDto(storeId, path).createSaveImgStoreBuilder();
            imgStoreIds.add(this.imgStoreRepository.save(imgStore).getId());
        }

        return imgStoreIds;
    }


}
