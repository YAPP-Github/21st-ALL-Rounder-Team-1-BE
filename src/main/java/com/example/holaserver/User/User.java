package com.example.holaserver.User;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Enumerated(EnumType.STRING)
    @Setter
    private Type type;

    private String oauthType;

    private String oauthIndentity;

    private Byte rating;

    private String imgPath;

    private Timestamp removedAt;
}
