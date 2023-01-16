package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@NoArgsConstructor
public class StoreSaveRequestDto {
    private Long id;
    private Long userId;
    private String name;
    private String latitude;
    private String longitude;
    private String businessHour;
    private String address;
    private String instaAccount;
    private String callNumber;
    private Integer remommendation;
    private String registrationNumber;
    private Date createdAt;
    private Date modifiedAt;
    private Date removedAt;
}
