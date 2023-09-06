package com.example.smilekarina.coupon.presentation;


import com.example.smilekarina.coupon.application.CouponService;
import com.example.smilekarina.coupon.dto.CouponDto;
import com.example.smilekarina.coupon.dto.CouponPartnerDto;
import com.example.smilekarina.coupon.vo.CouponAllSearchOut;
import com.example.smilekarina.coupon.vo.CouponGetIn;
import com.example.smilekarina.coupon.vo.CouponIn;
import com.example.smilekarina.coupon.vo.CouponPartnerIn;
import com.example.smilekarina.gift.vo.GiftIn;
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
    public ResponseEntity<?> couponAllSearch(@PathVariable(value = "orderType")Integer orderType,
                                             @RequestHeader("Authorization") String token,
                                             Pageable pageable) {
        Page<CouponAllSearchOut> couponData = couponService.getAllCouponWithUser(orderType,token,pageable);
        return ResponseEntity.ok(ResponseOut.success(couponData));
    }
    @PostMapping("/benefits/myCoupon")
    public ResponseEntity<?> couponToMine(@RequestHeader("Authorization") String token,
                                          @RequestBody CouponGetIn couponGetIn) {
        // 쿠폰 등록하면 해당 쿠폰을 내 쿠폰함에서 가져다 쓸수 있게 구현
        couponService.createMyCoupon(token,couponGetIn);
        return ResponseEntity.ok(ResponseOut.success());
    }
    @DeleteMapping("/benefits/myCoupon")
    public ResponseEntity<?> couponDeleteFromMine(@RequestHeader("Authorization") String token,
                                                  @RequestBody CouponGetIn couponGetIn) {
        couponService.deleteMyCoupon(token,couponGetIn);
        return ResponseEntity.ok(ResponseOut.success());
    }


    // admin 용도
    @PostMapping("/auth/coupon/partner")
    public ResponseEntity<?> partnerAdd(@RequestBody CouponPartnerIn couponPartnerIn) {
        couponService.createPartner(modelMapper.map(couponPartnerIn, CouponPartnerDto.class));
        return ResponseEntity.ok(ResponseOut.success());
    }
    @PostMapping("/auth/coupon/coupon")
    public ResponseEntity<?> couponAdd(@RequestBody CouponIn couponIn) {
//        log.info(couponIn.getCouponName());
        couponService.createCoupon(modelMapper.map(couponIn, CouponDto.class));
        return ResponseEntity.ok(ResponseOut.success());
    }

}
