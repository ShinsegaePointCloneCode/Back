package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByCouponStartAfterAndCouponEndOrderByCouponEndDesc(LocalDateTime eventStart, LocalDateTime eventEnd);

    List<Coupon> findByCouponStartAfterAndCouponEndOrderByCouponEndAsc(LocalDateTime eventStart, LocalDateTime eventEnd);

    List<Coupon> findByCouponEndAfter(LocalDateTime now);
}
