package com.hyeonah.myway.coupon;

import com.hyeonah.myway.domain.Coupon;
import com.hyeonah.myway.domain.Member;
import com.hyeonah.myway.member.MemberService;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Created by hyeonahlee on 2020-12-26.
 */
@Service
public class CouponService {

    private final MemberService memberService;
    private final CouponRepository couponRepository;

    public CouponService(final MemberService memberService, final CouponRepository couponRepository) {
        this.memberService = memberService;
        this.couponRepository = couponRepository;
    }

    public Coupon receiveCoupon(final Long memberId, final Coupon coupon) {
        final Optional<Member> member = memberService.findById(memberId);
        if(member.isPresent()) {
            coupon.setMemberId(memberId);
        } else {
            throw new IllegalArgumentException("Member doesn't exist for id:'" + memberId + "'");
        }

        final Coupon newCoupon = couponRepository.save(coupon);
        memberService.notify(newCoupon);
        return newCoupon;
    }

    public Coupon activeCoupon(final Coupon coupon) {
        coupon.active();
        final Coupon activeCoupon = couponRepository.save(coupon);
        memberService.notify(activeCoupon);
        return activeCoupon;
    }

}
