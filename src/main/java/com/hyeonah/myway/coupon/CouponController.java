package com.hyeonah.myway.coupon;

import com.hyeonah.myway.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyeonahlee on 2020-12-26.
 */
@RestController
@RequiredArgsConstructor
public class CouponController {

    final CouponRepository couponRepository;

    @GetMapping("/coupon/{id}")
    public Coupon findCouponById(@PathVariable final Long id) {
        return couponRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Coupon not found by '" + id + "'"));
    }

    @PostMapping("/coupon")
    public Coupon receiveCoupon(@RequestBody final Coupon coupon) {
        return couponRepository.save(coupon);
    }
}
