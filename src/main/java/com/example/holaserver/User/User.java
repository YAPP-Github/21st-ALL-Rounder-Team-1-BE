package com.example.holaserver.User;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Setter
    private Type type;

    private String oauthType;

    private String oauthIndentity;

    private Byte rating;

    private String imgPath;

    private Timestamp removedAt;

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public User(String name,String email, Type type, String oauthType, String oauthIndentity) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.oauthType = oauthType;
        this.oauthIndentity = oauthIndentity;
    }
}
