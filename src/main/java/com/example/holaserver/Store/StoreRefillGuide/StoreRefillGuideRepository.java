package com.example.holaserver.Store.StoreRefillGuide;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRefillGuideRepository extends JpaRepository<StoreRefillGuide, Long> {
    List<StoreRefillGuide> findStoreRefillGuidesByStoreId(Long storeId);
}
