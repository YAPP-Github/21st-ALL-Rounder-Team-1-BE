package com.example.holaserver.Store.ImgStore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgStoreRepository extends JpaRepository<ImgStore, Long> {
    List<ImgStore> findImgStoreByStoreId(Long storeId);
    void deleteImgStoresByStoreId(Long storeId);
}
