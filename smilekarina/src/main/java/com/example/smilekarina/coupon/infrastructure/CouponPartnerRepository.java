package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.CouponPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponPartnerRepository extends JpaRepository<CouponPartner,Long> {

}
