package com.example.smilekarina.coupon.presentation;


import com.example.smilekarina.coupon.application.CouponService;
import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import com.example.smilekarina.coupon.vo.CouponPartnerIn;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CouponController {
    private final CouponService couponService;
    private final ModelMapper modelMapper;
    @GetMapping("/couponPage/{orderType}")
    public ResponseEntity<?> couponAllSearch(@PathVariable(value = "orderType")String orderType,
                                             @RequestHeader("Authorization") String token,
                                             Pageable pageable) {
        Page<CouponAllSearchOut> couponData = couponService.getAllCoupon(orderType,token,pageable);
        return ResponseEntity.ok(ResponseOut.success());
    }
    // coupon data inject 용도
    @PostMapping("/auth/coupon/partner")
    public ResponseEntity<?> partnerAdd(@RequestBody CouponPartnerIn couponPartnerIn) {
        couponService.createPartner(modelMapper.map(couponPartnerIn, CouponPartnerDto.class));
        return ResponseEntity.ok(ResponseOut.success());
    }

}
