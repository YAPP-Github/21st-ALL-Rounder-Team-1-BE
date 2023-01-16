package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseTemplate<StoreResponseDto> createStore(@RequestBody )

}
