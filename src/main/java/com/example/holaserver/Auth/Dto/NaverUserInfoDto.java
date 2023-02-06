package com.example.holaserver.Auth.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverUserInfoDto {
    private response response;
    @Getter
    public class response {
        private String email;
        private String id;
        private String name;
    }
}
