package com.example.holaserver.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String nickname;
    private String imtPath;
    private Byte rating;

    @Builder
    public UserInfoResponse(User entity){
        this.nickname = entity.getNickname();
        this.imtPath = entity.getImgPath();
        this.rating = entity.getRating();
    }
}