package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.Coupon;
import com.example.smilekarina.coupon.domain.MyCouponList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MyCouponListRepository extends JpaRepository<MyCouponList, Long> {

    List<MyCouponList> findByCouponIdAndUserId(Long couponId, Long userId);
}
