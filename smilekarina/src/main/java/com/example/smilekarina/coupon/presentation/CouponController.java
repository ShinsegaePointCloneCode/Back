package com.example.smilekarina.coupon.presentation;


import com.example.smilekarina.coupon.application.CouponService;
import com.example.smilekarina.coupon.vo.CouponPageListOut;
import com.example.smilekarina.global.vo.ResponseOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/couponPage/coupon-download-page")
    public ResponseEntity<?>getIngCoupon(@RequestParam(value = "pageNo")Integer pageNo,
                                         @RequestParam(value = "size")Integer size,
                                         @RequestParam(value = "orderType")String orderType){
        List<CouponPageListOut> couponPageListOuts =couponService.checkIngCoupon(pageNo,size,orderType);
        ResponseOut<?> responseOut = ResponseOut.success();
        return ResponseEntity.ok(responseOut);
    }

    @GetMapping("/benefits/endCoupon")
     public ResponseEntity<?>getEndCoupon(@RequestParam(value = "searchType")String searchType,
                                          @RequestParam(value = "pageNo")Integer pageNo,
                                          @RequestParam(value = "size")Integer size){
        List<CouponPageListOut> couponPageListOuts = couponService.endCoupon(searchType,pageNo,size);
         ResponseOut<?> responseOut = ResponseOut.success();
         return ResponseEntity.ok(responseOut);
     }
}
