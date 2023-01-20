package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@NoArgsConstructor
public class SaveStoreRequestDto {
    private String name;
    private String latitude;
    private String longitude;
    private String businessHour;
    private String notice;
    private String address;
    private String imgPath;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;
    private Date createdAt;
    private Date modifiedAt;
    private Date removedAt;

    @Builder
    public Store toEntity() {
        return Store.builder()
                .name(name)
                .status("VIEW")
                .latitude(latitude)
                .longitude(longitude)
                .businessHour(businessHour)
                .notice(notice)
                .address(address)
                .imgPath(imgPath)
                .instaAccount(instaAccount)
                .callNumber(callNumber)
                .registrationNumber(registrationNumber)
                .build();
    }
}
