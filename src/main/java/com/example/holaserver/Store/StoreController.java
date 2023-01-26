package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.StoreBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeSave(@RequestBody StoreBody storeBody) {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(storeBody), "가게 정보 저장 성공");
    }

    @GetMapping("/user/store")
    public ResponseTemplate<Store> storeDetailsByUserId() throws DataFormatException {
        return new ResponseTemplate<>(storeService.findStoreByUserId(), "가게 정보 불러오기 성공");
    }

    @PutMapping("/store")
    public ResponseTemplate<Long> storeUpdate(@RequestBody StoreBody storeBody) {
        return new ResponseTemplate<>(storeService.updateStore(storeBody), "가게 정보 업데이트 성공");
    }
}
