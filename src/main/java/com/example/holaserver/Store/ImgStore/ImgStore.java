package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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

    public ImgStore(
            Long storeId,
            String path
    ) {
        this.storeId = storeId;
        this.path = path;
    }

}
