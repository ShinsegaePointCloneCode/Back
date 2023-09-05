package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.Coupon;
import com.example.smilekarina.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Long> {

}
