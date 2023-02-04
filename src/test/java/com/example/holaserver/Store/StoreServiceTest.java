//package com.example.holaserver.Store;
//
//import com.example.holaserver.Store.DTO.StoreBody;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.*;
//
//public class StoreServiceTest {
//    @Mock
//    StoreRepository storeRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void saveStore_는_StoreSaveRequestDto_를_받아_storeId_를_반환한다() {
//        // given
//        StoreBody storeDto = new StoreBody();
//        storeDto.createSaveStoreBuilder(123L);
//        // when
//        when(storeRepository.save(storeDto)).thenReturn(storeDto.createSaveStoreBuilder(123L));
//        Store store = storeRepository.save(storeDto.createSaveStoreBuilder(123L));
//
//        // then
//        verify(storeRepository, times(1)).save((Store) notNull());
//        Assertions.assertEquals(store.getUserId(), 123L);
//    }
//}