package com.example.holaserver.User.Dto;

import com.example.holaserver.User.Enum.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BossSaveBody {
    private String name;
    private String email;
    private String phoneNumber;
    private Type type;
}
