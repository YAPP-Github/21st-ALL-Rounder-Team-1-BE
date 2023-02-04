//package com.example.holaserver.Store;
//
//import com.example.holaserver.Common.response.ResponseTemplate;
//import com.example.holaserver.Store.DTO.StoreBody;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.zip.DataFormatException;
//
//import static org.mockito.Mockito.*;
//
//public class StoreControllerTest {
//    @Mock
//    StoreService storeService;
//
//    @InjectMocks
//    StoreController storeController;
//
//    Store store = new Store(
//            1L,
//            2L,
//            "yunmin",
//            "View",
//            "1234.4321",
//            "4321.1234",
//            "businessHour",
//            "휴무",
//            "수원",
//            "@yunmin",
//            "010-1234-4321",
//            "110-321",
//            true,
//            true
//    );
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void storeSave_는_StoreSaveBody_를_받아_가게_ID_와_가게사진_ID_를_반환한다() {
//        // given
//        StoreBody storeSaveBody = new StoreBody();
//        Map<String, Object> mockResult = new HashMap<String, Object>();
//        List<Long> mockImgStoreIds = new ArrayList<Long>();
//        mockImgStoreIds.add(1L);
//        mockImgStoreIds.add(2L);
//        mockResult.put("storeId", 1);
//        mockResult.put("imgStoreIds", mockImgStoreIds);
//
//        // when
//        when(storeService.saveStoreAndRelationInfo(storeSaveBody, false)).thenReturn(mockResult);
//        ResponseTemplate<Map<String, Object>> result = storeController.storeSave(storeSaveBody);
//
//        // then
//        verify(storeService, times(1)).saveStoreAndRelationInfo(storeSaveBody, false);
//        Assertions.assertEquals(result.getData().get("storeId"), 1);
//        Assertions.assertEquals(result.getData().get("imgStoreIds"), mockImgStoreIds);
//    }
//
//    @Test
//    public void storeDetailsByUserId는_유저_ID에_해당하는_가게정보를_반환한다 () throws DataFormatException {
//        // given
//
//        // when
//        when(storeService.findStoreByUserId()).thenReturn(store);
//        ResponseTemplate<Store> result = storeController.storeDetailsByUserId();
//
//        // then
//        verify(storeService, times(1)).findStoreByUserId();
//        Assertions.assertEquals(result.getData().getName(), "yunmin");
//    }
//}