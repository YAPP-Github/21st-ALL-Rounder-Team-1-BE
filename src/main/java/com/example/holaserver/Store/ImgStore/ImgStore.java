package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class ImgStore extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private String path;

    private Date removedAt;

    @Builder
    public ImgStore(
            Long storeId,
            String path
    ) {
        this.storeId = storeId;
        this.path = path;
    }

}
