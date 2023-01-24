package com.example.holaserver.Auth.Dto;

import com.example.holaserver.User.Type;
import com.example.holaserver.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginResponse {
    private String name;
    private String email;
    private String imgPath;
    private String oauthIdentity;
    private String jwt;
    private String refreshToken;


    @Builder
    public KakaoLoginResponse(SocialUserInfoDto user,String oauthIdentity, String token){
        this.name = user.getKakao_account().getProfile().getNickname();
        this.email = user.getKakao_account().getEmail();
        this.imgPath = user.getKakao_account().getProfile().getProfile_image_url();
        this.oauthIdentity = oauthIdentity;
        this.jwt = token;
    }
}
