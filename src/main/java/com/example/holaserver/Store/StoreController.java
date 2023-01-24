package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.StoreSaveBody;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.NotSupportedException;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/store")
    public ResponseTemplate<Map<String, Object>> storeSave(@RequestBody StoreSaveBody storeSaveBody) {
        return new ResponseTemplate<>(storeService.saveStoreAndRelationInfo(storeSaveBody), "가게 정보 저장 성공");
    }

    @GetMapping("/user/store")
    public ResponseTemplate<Store> storeDetailsByUserId() throws DataFormatException {
        return new ResponseTemplate<>(storeService.findStoreByUserId(), "가게 정보 불러오기 성공");
    }

}
