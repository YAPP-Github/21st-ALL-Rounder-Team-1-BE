package com.example.holaserver.Store;

import com.example.holaserver.Store.DTO.SaveStoreRequestDto;
import com.example.holaserver.Store.ImgStore.ImgStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ImgStoreService imgStoreService;

    @Transactional
    public Long saveStore(SaveStoreRequestDto storeDto) {
        Long storeId = storeRepository.save(storeDto.createSaveStoreBuilder()).getId();
        List<Long> imgPathIds = this.imgStoreService.saveImgStore(storeId, storeDto.getImgPath());
        if (imgPathIds.size() == 0) throw new Error("이미지 저장 에러");
        return storeId;
    }
}
