package com.example.holaserver.User.Dto;

import com.example.holaserver.User.Enum.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveBody {
    private String name;
    private String email;
    private String imgPath;
    private Type type;
    private String oauthType;
    private String oauthIdentity;
}
