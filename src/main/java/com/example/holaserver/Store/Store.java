package com.example.holaserver.Store;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@Where(clause = "removed_at IS NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "business_hour")
    private String businessHour;
    @Column(name = "notice")
    private String notice;
    @Column(name = "address")
    private String address;
    @Column(name = "insta_account")
    private String instaAccount;
    @Column(name = "call_number")
    private String callNumber;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "is_day_off")
    private Boolean isDayOff;
    @Column(name = "is_ready")
    private Boolean isReady;
    private Timestamp removedAt;

    public void removeStore() {
        this.removedAt = new Timestamp(System.currentTimeMillis());
    }
}
