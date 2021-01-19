package com.hyeonah.myway.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hyeonahlee on 2020-12-26.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    private String code;
    private CouponStatus status = CouponStatus.DRAFT;
    private String name;
    private int limitCount;
    private LocalDateTime expiredDateTime;

    public Coupon(final int limit, final String name) {
        this.limitCount = limit;
        this.name = name;
    }

    public Coupon(final int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야 합니다.");
        }
        this.limitCount = limit;
    }

    public void active() {
        this.expiredDateTime = LocalDateTime.now().plusDays(1);
        this.status = CouponStatus.ACTIVE;
    }

    public void expire() {
        this.expiredDateTime = LocalDateTime.now();
        this.status = CouponStatus.ENDED;
    }
}
