package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.Store;
import lombok.*;

@Getter
@NoArgsConstructor
public class StoreBody {
    private Long id;
    private Long userId;
    private String name;
    private String latitude;
    private String longitude;
    private String businessHour;
    private String notice;
    private String address;
    private String[] imgPath;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;
    private Boolean isReady;

    @Builder
    public Store createSaveStoreBuilder(Long userId) {
        return Store.builder()
                .userId(userId)
                .name(name)
                .status("VIEW")
                .latitude(latitude)
                .longitude(longitude)
                .businessHour(businessHour)
                .notice(notice)
                .address(address)
                .instaAccount(instaAccount)
                .callNumber(callNumber)
                .registrationNumber(registrationNumber)
                .isReady(false)
                .build();
    }

    @Builder
    public Store updateStoreStatusBuilder(Long id, Store storeData, Boolean isReady) {
        return Store.builder()
                .id(id)
                .userId(storeData.getUserId())
                .name(storeData.getName())
                .status("READY")
                .latitude(storeData.getLatitude())
                .longitude(storeData.getLongitude())
                .businessHour(storeData.getBusinessHour())
                .notice(storeData.getNotice())
                .address(storeData.getAddress())
                .instaAccount(storeData.getInstaAccount())
                .callNumber(storeData.getCallNumber())
                .registrationNumber(storeData.getRegistrationNumber())
                .isDayOff(storeData.getIsDayOff())
                .isReady(isReady)
                .build();
    }

    @Builder
    public Store updateStoreBuilder(StoreBody storeBody, Long userId) {
        return Store.builder()
                .id(storeBody.getId())
                .userId(userId)
                .name(storeBody.getName())
                .status("VIEW")
                .latitude(storeBody.getLatitude())
                .longitude(storeBody.getLongitude())
                .businessHour(storeBody.getBusinessHour())
                .notice(storeBody.getNotice())
                .address(storeBody.getAddress())
                .instaAccount(storeBody.getInstaAccount())
                .callNumber(storeBody.getCallNumber())
                .registrationNumber(storeBody.getRegistrationNumber())
                .isReady(true)
                .build();

    }
}
