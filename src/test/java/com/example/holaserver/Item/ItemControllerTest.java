//package com.example.holaserver.Item;
//
//import com.example.holaserver.Common.response.ResponseTemplate;
//import com.example.holaserver.Item.DTO.ItemSaveBody;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class ItemControllerTest {
//    @Mock
//    ItemService itemService;
//    @InjectMocks
//    ItemController itemController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void itemSave는_가게ID와_상품리스트를_받아_상품IDs를_반환한다() {
//        // given
//        Long storeId = 10L;
//        Map<String, Object> mockResult = new HashMap<String, Object>();
//        mockResult.put("itemIds", 1);
//        mockResult.put("itemIds", 2);
//        when(itemService.saveItems(storeId, new ItemSaveBody[1], false)).thenReturn(mockResult);
//        // when
//        ResponseTemplate<Map<String, Object>> result = itemController.itemSave(new ItemSaveBody[1], storeId);
//
//
//        // then
//        verify(itemService, times(1)).saveItems(storeId, new ItemSaveBody[1], false);
//        Assertions.assertEquals(result.getData(), mockResult);
//    }
//
//}