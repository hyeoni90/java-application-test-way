package com.hyeonah.myway.member;

import com.hyeonah.myway.domain.Coupon;
import com.hyeonah.myway.domain.Member;
import java.util.Optional;

/**
 * Created by hyeonahlee on 2020-12-26.
 */
public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Coupon newCoupon);

    void notify(Member member);
}
