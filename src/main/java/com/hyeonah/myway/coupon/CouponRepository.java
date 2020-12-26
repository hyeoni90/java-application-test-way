package com.hyeonah.myway.coupon;

import com.hyeonah.myway.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hyeonahlee on 2020-12-26.
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
