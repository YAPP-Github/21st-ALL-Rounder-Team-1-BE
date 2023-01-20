package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.SaveStoreRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseTemplate<Long> saveStore(@RequestBody SaveStoreRequestDto saveRequestDto) throws Exception {
        return new ResponseTemplate<>(storeService.saveStore(saveRequestDto), "가게 정보 저장 성공");
    }
}
