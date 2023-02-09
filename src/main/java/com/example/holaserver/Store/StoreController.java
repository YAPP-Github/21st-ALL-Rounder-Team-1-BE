package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/user/store")
    public ResponseTemplate<StoreResponse> storeDetailsByUserId() throws DataFormatException {
        return new ResponseTemplate<>(storeService.findStoreByUserId(), "가게 정보 불러오기 성공");
    }

    @PostMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeSave(@RequestBody StoreBody storeBody) {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(storeBody, false), "가게 정보 저장 성공");
    }

    @PatchMapping ("/store")
    public ResponseTemplate<Map<String, Object>> storeUpdate(@RequestBody StoreBody storeBody) {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(storeBody, true), "가게 정보 업데이트 성공");
    }

    @DeleteMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeDelete(@RequestBody StoreDeleteBody storeDeleteBody) {
        return new ResponseTemplate<>(storeService.deleteStoreById(storeDeleteBody), "가게 정보 삭제 완료");
    }

    @GetMapping("/user/stores")
    public ResponseTemplate<List<StoreByLongitudeAndLatitudeResponse>> storeListByAddress(
            @RequestParam String longitude,
            @RequestParam String latitude
            ) {
        return new ResponseTemplate<>(storeService.findStoresByLongitudeAndLatitude(longitude, latitude), "유저 위치 가게 정보 불러오기 성공");
    }



}
