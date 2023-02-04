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
        this.name = user.getKakao_account().getProfile().getNickname();
        this.email = user.getKakao_account().getEmail();
        this.oauthType = "Kakao";
        this.imgPath = user.getKakao_account().getProfile().getProfile_image_url();
        this.oauthIdentity = oauthIdentity;
        this.jwt = token;
    }

    @Builder
    public SocialLoginResponse(String oauthIdentity, String token){
        this.oauthType = "Apple";
        this.oauthIdentity = oauthIdentity;
        this.jwt = token;
    }
}
