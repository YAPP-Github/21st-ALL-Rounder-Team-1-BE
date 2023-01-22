package com.example.holaserver.Store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StoreControllerTest {
    @Mock
    StoreService storeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void storeSave_는_StoreSaveParameter_를_받아_가게_ID_와_가게사진_ID_를_반환한다() {
        // given

        // when

        // then
    }


}