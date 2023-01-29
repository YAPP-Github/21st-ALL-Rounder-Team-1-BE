package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.Store.Store;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImgStore extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storeId;
    private String path;

    public ImgStore(Long storeId, String path) {
        this.storeId = storeId;
        this.path = path;
    }
}
