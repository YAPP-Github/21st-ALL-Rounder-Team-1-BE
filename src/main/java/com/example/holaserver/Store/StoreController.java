package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.StoreSaveParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeSave(@RequestBody StoreSaveParameter saveRequestDto) throws Exception {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(saveRequestDto), "가게 정보 저장 성공");
    }
}
