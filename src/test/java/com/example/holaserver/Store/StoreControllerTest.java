package com.example.holaserver.Store;

import com.example.holaserver.Store.DTO.StoreSaveParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class StoreControllerTest {
    @Mock
    StoreService storeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void storeSave_는_StoreSaveParameter_를_받아_가게_ID_와_가게사진_ID_를_반환한다() {
        // given
        StoreSaveParameter storeSaveParameter = new StoreSaveParameter();
        Map<String, Object> mockResult = new HashMap<String, Object>();
        List<Long> mockImgStoreIds = new ArrayList<Long>();
        mockImgStoreIds.add(1L);
        mockImgStoreIds.add(2L);
        mockResult.put("storeId", 1);
        mockResult.put("imgStoreIds", mockImgStoreIds);
        when(storeService.saveStoreAndRelationInfo(storeSaveParameter)).thenReturn(mockResult);

        // when
        Map<String, Object> result = storeService.saveStoreAndRelationInfo(storeSaveParameter);

        // then
        verify(storeService, times(1)).saveStoreAndRelationInfo(storeSaveParameter);
        Assertions.assertEquals(result.get("storeId"), 1);
        Assertions.assertEquals(result.get("imgStoreIds"), mockImgStoreIds);
    }
}