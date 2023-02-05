package com.example.holaserver.Auth.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {

    private Long id;
    private kakao_account kakao_account;

    @Getter
    public class kakao_account {
        private String email;
        private String name;
        private profile profile;
    }

    @Getter
    public static class profile {
        private String nickname;
        private String profile_image_url;
    }
}
