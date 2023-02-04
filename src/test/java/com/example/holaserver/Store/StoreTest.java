//package com.example.holaserver.Store;
//
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class StoreTest {
//    Store store = new Store(
//            1L,
//            1L,
//            "yunmin",
//            "VIEW",
//            "1234",
//            "4321",
//            "10~22",
//            "화 휴무",
//            "경기도 수원시",
//            "@yunmin",
//            "010-1234-1234",
//            "110-110",
//            true,
//            false);
//    @Test
//    public void Store_모델에서_getName을_호출하면_가게이름이_반환된다() {
//        assertEquals("yunmin", store.getName());
//    }
//
//    @Test
//    public void Store_모델을_생성자없이_생성하면_Store_name_은_null_이_반환된다() {
//        Store _store = new Store();
//        assertEquals(null, _store.getName());
//    }
//}