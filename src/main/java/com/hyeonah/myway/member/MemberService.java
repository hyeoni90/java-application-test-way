package com.hyeonah.myway.member;

import com.hyeonah.myway.domain.Member;
import java.util.Optional;

/**
 * Created by hyeonahlee on 2021-01-19.
 */
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findById(final Long memberId) {
        return memberRepository.findById(memberId);
    }

}
