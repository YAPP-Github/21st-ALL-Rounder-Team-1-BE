package com.example.holaserver.Store;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Store.DTO.StoreBody;
import com.example.holaserver.Store.DTO.StoreByLongitudeAndLatitudeInterface;
import com.example.holaserver.Store.DTO.StoreByLongitudeAndLatitudeResponse;
import com.example.holaserver.Store.DTO.StoreDeleteBody;
import com.example.holaserver.Store.ImgStore.ImgStore;
import com.example.holaserver.Store.ImgStore.ImgStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ImgStoreService imgStoreService;
    private final AuthService authService;

    @Transactional
    public Map<String, Object> saveStoreAndRelationInfo(StoreBody storeDto, Boolean isUpdate) {
        ModelMap result = new ModelMap();
        Long storeId; List<Long> imgPathIds;

        if (isUpdate) storeId = this.updateStore(storeDto);
        else storeId = this.saveStore(storeDto);

        imgPathIds = this.saveImgStores(storeId, storeDto.getImgPath());
        if (imgPathIds.size() == 0) throw new Error("이미지 저장 에러");

        result.addAttribute("storeId", storeId);
        result.addAttribute("imgStoreIds", imgPathIds);
        return result;
    }

    @Transactional
    public Map<String, Object> deleteStoreById(StoreDeleteBody storeDeleteBody) {
        ModelMap result = new ModelMap();
        this.deleteStore(storeDeleteBody.getStoreId());
        result.addAttribute("storeId", storeDeleteBody.getStoreId());
        return result;
    }

    private Long saveStore(StoreBody storeDto) {
        return storeRepository.save(storeDto.createSaveStoreBuilder(authService.getPayloadByToken())).getId();
    }

    private Long updateStore(StoreBody storeDto) {
        return storeRepository.save(storeDto.updateStoreBuilder(storeDto, authService.getPayloadByToken())).getId();
    }

    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }
    
    private List<Long> saveImgStores(Long storeId, String[] pathDatas) {
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

    public List<StoreByLongitudeAndLatitudeResponse> findStoresByLongitudeAndLatitude(String longitude, String latitude) {
        List<StoreByLongitudeAndLatitudeInterface> stores = this.storeRepository.findStoreByLatitudeAndLongitude(longitude, latitude);
        return stores.stream().map(store -> {
            List<ImgStore> imgStores = imgStoreService.findImgStoreByStoreId(store.getId());
            return new StoreByLongitudeAndLatitudeResponse(store, imgStores);
        }).collect(Collectors.toList());
    }
}
