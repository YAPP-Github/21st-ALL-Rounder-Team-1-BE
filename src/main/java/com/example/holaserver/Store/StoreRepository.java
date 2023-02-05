package com.example.holaserver.Store;

import com.example.holaserver.Store.DTO.StoreByLongitudeAndLatitudeInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByUserId(Long userId);

    Boolean existsStoreById(Long storeId);

    @Query(value = "SELECT\n" +
            "    id, user_id, name, status, latitude, longitude, business_hour, notice, address," +
            "   insta_account, call_number, registration_number, is_ready,\n" +
            "    (6371*acos(cos(radians(:latitude))*cos(radians(latitude))*\n" +
            "               cos(radians(longitude)-radians(:longitude))+sin(radians(:latitude))\n" +
            "                                                        *sin(radians(latitude))))AS distance\n" +
            "FROM store\n" +
            "ORDER BY distance;", nativeQuery = true)
    List<StoreByLongitudeAndLatitudeInterface> findStoreByLatitudeAndLongitude(
            @Param(value = "longitude") String longitude ,
            @Param(value = "latitude") String latitude
    );
}
