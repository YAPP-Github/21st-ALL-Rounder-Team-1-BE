package com.example.holaserver.Store.ImgStore;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Getter
@Builder
@Where(clause = "removed_at IS NULL")
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
