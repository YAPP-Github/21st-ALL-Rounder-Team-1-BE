package com.example.holaserver.Store;

import com.example.holaserver.Item.DTO.ItemSaveDto;
import com.example.holaserver.Item.ItemService;
import com.example.holaserver.Store.DTO.StoreSaveParameter;
import com.example.holaserver.Store.ImgStore.ImgStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ImgStoreService imgStoreService;
    private final ItemService itemService;

    @Transactional
    public Map<String, Object> saveStoreAndRelationInfo(StoreSaveParameter storeDto) {
        Long storeId = this.saveStore(storeDto);
        List<Long> imgPathIds = this.saveImgStores(storeId, storeDto.getImgPath());
        if (imgPathIds.size() == 0) throw new Error("이미지 저장 에러");
        ModelAndView result = new ModelAndView();
        result.addObject("storeId", storeId);
        result.addObject("imgStoreIds", imgPathIds);
        return result.getModel();
    }

    private Long saveStore(StoreSaveParameter storeDto) {
        return storeRepository.save(storeDto.createSaveStoreBuilder(123L)).getId();
    }
    
    private List<Long> saveImgStores(Long storeId, String pathDatas) {
        return this.imgStoreService.saveImgStores(storeId, pathDatas);
    }
}
