package com.example.holaserver.Store;

import com.example.holaserver.Item.DTO.ItemSaveDto;
import com.example.holaserver.Store.DTO.StoreSaveParameter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest
public class StoreServiceTest {
    ItemSaveDto[] itemSaveDtos = new ItemSaveDto[0];

    @Mock
    StoreRepository storeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveStore_는_StoreSaveRequestDto_를_받아_storeId_를_반환한다() {
        // given
        MockitoAnnotations.initMocks(this);
        StoreSaveParameter storeDto = new StoreSaveParameter(
                "name",
                "1234",
                "4321",
                "[{day: 월, time: 10:00~22:00},{day: 화, time: 10:00~22:00}]",
                "휴무일 월요일",
                "경기도 수원시",
                "[\"path1\",\"path2\",\"path3\"]",
                "@yunmin",
                "010-1234-4321",
                "110-4321",
                itemSaveDtos
        );
        // when
        when(storeRepository.save((Store) notNull())).thenReturn(storeDto.createSaveStoreBuilder(123L));
        Store store = storeRepository.save(storeDto.createSaveStoreBuilder(123L));

        verify(storeRepository, times(1)).save((Store) notNull());
        assertEquals(store.getUserId(), 123L);
    }
}