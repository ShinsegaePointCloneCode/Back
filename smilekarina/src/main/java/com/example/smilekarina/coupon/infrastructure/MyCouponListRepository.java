package com.example.smilekarina.coupon.infrastructure;

import com.example.smilekarina.coupon.domain.MyCouponList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCouponListRepository extends JpaRepository<MyCouponList, Long> {

}
