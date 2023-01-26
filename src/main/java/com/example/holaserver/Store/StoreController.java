package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.StoreBody;
import com.example.holaserver.Store.DTO.StoreByAddressResponse;
import com.example.holaserver.Store.DTO.StoreByLatitudeAndLongitudeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeSave(@RequestBody StoreBody storeBody) {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(storeBody, false), "가게 정보 저장 성공");
    }

    @GetMapping("/user/store")
    public ResponseTemplate<Store> storeDetailsByUserId() throws DataFormatException {
        return new ResponseTemplate<>(storeService.findStoreByUserId(), "가게 정보 불러오기 성공");
    }

    @PutMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeUpdate(@RequestBody StoreBody storeBody) {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(storeBody, true), "가게 정보 업데이트 성공");
    }

    @GetMapping("/user/{longitude}/{latitude}/stores")
    public ResponseTemplate<List<StoreByLatitudeAndLongitudeResponse>> storeListByAddress(
            @PathVariable String longitude,
            @PathVariable String latitude
            ) {
        return new ResponseTemplate<>(storeService.findStoresByLongitudeAndLatitude(longitude, latitude), "유저 위치 가게 정보 불러오기 성공");
    }

}
