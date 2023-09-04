package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.Coupon;
import com.example.smilekarina.coupon.domain.MyCouponList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MyCouponListRepository extends JpaRepository<MyCouponList, Long> {
    List<MyCouponList> findByCouponEndAfter(LocalDateTime now);
}
