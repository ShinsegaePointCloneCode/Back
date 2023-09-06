package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.Coupon;
import com.example.smilekarina.coupon.domain.MyCouponList;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MyCouponListRepository extends JpaRepository<MyCouponList, Long> {

}
