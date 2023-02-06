package com.example.holaserver.Auth.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SocialLoginResponse {
    private String name;
    private String email;
    private String imgPath;

    private String oauthType;
    private String oauthIdentity;
    private String jwt;
    private String refreshToken;


    @Builder
    public SocialLoginResponse(KakaoUserInfoDto user, String oauthIdentity, String token){
        if(user.getKakao_account().getProfile() != null){
            this.name = user.getKakao_account().getProfile().getNickname();
            this.imgPath = user.getKakao_account().getProfile().getProfile_image_url();
        }
        this.email = user.getKakao_account().getEmail();
        this.oauthType = "KAKAO";
        this.oauthIdentity = oauthIdentity;
        this.jwt = token;
    }

    @Builder
    public SocialLoginResponse(String oauthIdentity, String token){
        this.oauthType = "APPLE";
        this.oauthIdentity = oauthIdentity;
        this.jwt = token;
    }
}
