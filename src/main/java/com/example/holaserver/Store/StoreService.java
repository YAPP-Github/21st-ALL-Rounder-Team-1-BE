package com.example.holaserver.Store;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Store.DTO.*;
import com.example.holaserver.Store.ImgStore.ImgStore;
import com.example.holaserver.Store.ImgStore.ImgStoreService;
import com.example.holaserver.Store.StoreRefillGuide.StoreRefillGuide;
import com.example.holaserver.Store.StoreRefillGuide.StoreRefillGuideRepository;
import com.example.holaserver.Store.StoreRefillGuide.StoreRefillGuideService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ImgStoreService imgStoreService;
    private final StoreRefillGuideService storeRefillGuideService;
    private final AuthService authService;

    @Transactional
    public Map<String, Object> saveStoreAndRelationInfo(StoreBody storeDto, Boolean isUpdate) {
        ModelMap result = new ModelMap();
        Long storeId; List<Long> imgPathIds;

        if (isUpdate) {
            boolean isExist = storeRepository.existsById(storeDto.getId());
            if (!isExist) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업데이트 대상 가게가 없습니다.");
            storeId = this.updateStore(storeDto);
        }
        else {
            if (storeDto.getId() != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가게 신규 생성때는 ID를 넣으면 안됩니다.");
            storeId = this.saveStoreByUserId(storeDto);
        }
        this.removedImgStoresByStoreId(storeId);
        imgPathIds = this.saveImgStores(storeId, storeDto.getImgPath());

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

    private Long saveStoreByUserId(StoreBody storeDto) {
        return storeRepository.save(storeDto.createSaveStoreBuilder(authService.getPayloadByToken())).getId();
    }

    private Long updateStore(StoreBody storeDto) {
        return storeRepository.save(storeDto.updateStoreBuilder(storeDto, authService.getPayloadByToken())).getId();
    }

    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "이 아이디에 해당하는 가게가 없습니다."));
        store.removeStore();
    }

    private List<Long> saveImgStores(Long storeId, String[] pathDatas) {
        if (pathDatas == null) return new ArrayList<Long>();
        return this.imgStoreService.saveImgStores(storeId, pathDatas);
    }

    private void removedImgStoresByStoreId(Long storeId) {
        imgStoreService.deleteByStoreId(storeId);
    }

    public void updateStoreStatusById(Long storeId, Boolean isReady) {
        StoreBody storeBody = new StoreBody();
        Optional<Store> storeResult = Optional.ofNullable(this.storeRepository.findById(storeId)
                .orElseThrow(NoSuchElementException::new));
        storeResult.ifPresent(store -> this.storeRepository.save(storeBody.updateStoreStatusBuilder(storeId, store, isReady)));
    }

    /* 해당 유저가 가지고 있는 가게 2개 이상일 시 Error */
    public Map<String, Object> findStoreByUserId() {
        ModelMap result = new ModelMap();
        // TODO: 아직 확정 아니라서 stash OR 반영
        authService.getPayloadByToken();
        if (!storeRepository.findByUserId(authService.getPayloadByToken()).isPresent()) return result.addAttribute("result", null);
        Store store = storeRepository.findByUserId(authService.getPayloadByToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "이 유저가 속해있는 가게가 없습니다"));
        List<ImgStore> imgStores = imgStoreService.findImgStoreByStoreId(store.getId());

        return result.addAttribute("result", new StoreResponse(store, imgStores));
    }

    public List<StoreByLongitudeAndLatitudeResponse> findStoresByLongitudeAndLatitude(String longitude, String latitude) {
        authService.getPayloadByToken();
        List<StoreByLongitudeAndLatitudeInterface> stores = this.storeRepository.findStoreByLatitudeAndLongitude(longitude, latitude);

        return stores.stream().map(store -> {
            List<ImgStore> imgStores = imgStoreService.findImgStoreByStoreId(store.getId());
            List<StoreRefillGuide> storeRefillGuides = storeRefillGuideService.findAllByStoreId(store.getId());
            try {
                return new StoreByLongitudeAndLatitudeResponse(store, imgStores, storeRefillGuides);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Boolean existStoreById(Long storeId) {
        return storeRepository.existsStoreById(storeId);
    }

    public List<StoreRefillGuide> findRefillGuideByStoreId(Long storeId) {
        authService.getPayloadByToken();
        return storeRefillGuideService.findAllByStoreId(storeId);
    }
}
