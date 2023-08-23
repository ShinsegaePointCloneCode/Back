package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.CouponList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponListRepository extends JpaRepository<CouponList, Long> {
}
