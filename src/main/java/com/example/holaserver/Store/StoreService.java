package com.example.holaserver.Store;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Store.DTO.StoreBody;
import com.example.holaserver.Store.ImgStore.ImgStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ImgStoreService imgStoreService;
    private final AuthService authService;

    @Transactional
    public Map<String, Object> saveStoreAndRelationInfo(StoreBody storeDto) {
        Long storeId = this.saveStore(storeDto);
        List<Long> imgPathIds = this.saveImgStores(storeId, storeDto.getImgPath());
        if (imgPathIds.size() == 0) throw new Error("이미지 저장 에러");
        ModelAndView result = new ModelAndView();
        result.addObject("storeId", storeId);
        result.addObject("imgStoreIds", imgPathIds);
        return result.getModel();
    }

    private Long saveStore(StoreBody storeDto) {
        return storeRepository.save(storeDto.createSaveStoreBuilder(authService.getPayloadByToken())).getId();
    }
    
    private List<Long> saveImgStores(Long storeId, String pathDatas) {
        return this.imgStoreService.saveImgStores(storeId, pathDatas);
    }

    public void updateStoreStatusById(Long storeId, Boolean isReady) {
        StoreBody storeBody = new StoreBody();
        Optional<Store> storeResult = Optional.ofNullable(this.storeRepository.findById(storeId)
                .orElseThrow(NoSuchElementException::new));
        storeResult.ifPresent(store -> this.storeRepository.save(storeBody.updateStoreStatusBuilder(storeId, store, isReady)));
    }

    /* 해당 유저가 가지고 있는 가게 2개 이상일 시 Error */
    public Store findStoreByUserId() throws DataFormatException {
        return storeRepository.findByUserId(authService.getPayloadByToken()).orElseThrow(DataFormatException::new);
    }

    public Long updateStore(StoreBody storeDto) {

        return storeRepository.save(storeDto.updateStoreBuilder(storeDto, authService.getPayloadByToken())).getId();
    }
}
