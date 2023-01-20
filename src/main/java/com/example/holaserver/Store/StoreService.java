package com.example.holaserver.Store;

import com.example.holaserver.Store.DTO.SaveStoreRequestDto;
import com.example.holaserver.Store.ImgStore.ImgStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ImgStoreService imgStoreService;

    public Long saveStore(SaveStoreRequestDto storeDto) {
        Store store = this.makeSaveStoreBuilder(storeDto);


        return storeRepository.save(store).getId();
    }

    private Store makeSaveStoreBuilder(SaveStoreRequestDto storeDto) {
        return Store.builder()
                .name(storeDto.getName())
                .latitude(storeDto.getLatitude())
                .longitude(storeDto.getLongitude())
                .businessHour(storeDto.getBusinessHour())
                .address(storeDto.getAddress())
                .instaAccount(storeDto.getInstaAccount())
                .callNumber(storeDto.getCallNumber())
                .registrationNumber(storeDto.getRegistrationNumber())
                .build();
    }
}
