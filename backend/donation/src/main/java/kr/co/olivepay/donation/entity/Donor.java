package kr.co.olivepay.donation.entity;

import jakarta.persistence.*;
import kr.co.olivepay.donation.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Donor extends BaseEntity {
    @Id
    @Column(name = "donor_id", nullable = false, columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 11)
    private String phoneNumber;
}