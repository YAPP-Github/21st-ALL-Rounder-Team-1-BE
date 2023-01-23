package com.example.holaserver.Store.ImgStore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgStoreRepository extends JpaRepository<ImgStore, Long> {
}
