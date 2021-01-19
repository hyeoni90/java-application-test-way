package com.hyeonah.myway.member;

import com.hyeonah.myway.domain.Coupon;
import com.hyeonah.myway.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hyeonahlee on 2020-12-26.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Coupon newCoupon);

    void notify(Member member);
}
