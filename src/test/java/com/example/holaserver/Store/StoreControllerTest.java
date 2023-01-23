package com.example.holaserver.Store;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.DTO.StoreSaveBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class StoreControllerTest {
    @Mock
    StoreService storeService;

    @InjectMocks
    StoreController storeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void storeSave_는_StoreSaveBody_를_받아_가게_ID_와_가게사진_ID_를_반환한다() {
        // given
        StoreSaveBody storeSaveBody = new StoreSaveBody();
        Map<String, Object> mockResult = new HashMap<String, Object>();
        List<Long> mockImgStoreIds = new ArrayList<Long>();
        mockImgStoreIds.add(1L);
        mockImgStoreIds.add(2L);
        mockResult.put("storeId", 1);
        mockResult.put("imgStoreIds", mockImgStoreIds);
        when(storeService.saveStoreAndRelationInfo(storeSaveBody)).thenReturn(mockResult);


        // when
        // TODO: return result null
        ResponseTemplate<Map<String, Object>> result = storeController.storeSave(new StoreSaveBody());

        // then
//        verify(storeService, times(1)).saveStoreAndRelationInfo(storeSaveBody);
        Assertions.assertEquals(result.getData().get("storeId"), 1);
        Assertions.assertEquals(result.getData().get("imgStoreIds"), mockImgStoreIds);
    }
}