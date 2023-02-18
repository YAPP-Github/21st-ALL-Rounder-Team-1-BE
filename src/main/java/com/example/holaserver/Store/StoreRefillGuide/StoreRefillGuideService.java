package com.example.holaserver.Store.StoreRefillGuide;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreRefillGuideService {
    private final StoreRefillGuideRepository storeRefillGuideRepository;
    public List<StoreRefillGuide> findAllByStoreId(Long storeId) {
        return storeRefillGuideRepository.findStoreRefillGuidesByStoreId(storeId);
    }
}
