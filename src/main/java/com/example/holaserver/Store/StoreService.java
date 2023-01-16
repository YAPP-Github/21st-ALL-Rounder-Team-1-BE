package com.example.holaserver.Store;

import com.example.holaserver.Store.DTO.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Class<? extends Store> saveStore(StoreSaveRequestDto storeDto) {
        Store store = Store.builder()
                .userId(storeDto.getUserId())
                .name(storeDto.getName())
                .latitude(storeDto.getLatitude())
                .longitude(storeDto.getLongitude())
                .businessHour(storeDto.getBusinessHour())
                .address(storeDto.getAddress())
                .instaAccount(storeDto.getInstaAccount())
                .callNumber(storeDto.getCallNumber())
                .remommendation(storeDto.getRemommendation())
                .registrationNumber(storeDto.getRegistrationNumber())
                .build();
        return storeRepository.save(store).getClass();
    }
}
