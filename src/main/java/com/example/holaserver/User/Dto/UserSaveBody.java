package com.example.holaserver.User.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveBody {
    private String name;
    private String email;
    private String imgPath;
    private String oauthType;
    private String oauthIdentity;
}
