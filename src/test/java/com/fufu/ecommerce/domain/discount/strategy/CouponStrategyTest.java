package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.discount.Coupon;
import com.fufu.ecommerce.domain.discount.Discount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponStrategyTest {

    CouponStrategy couponStrategy = new CouponStrategy();

    @Test
    void givenCampaignStrategy_shouldCalculateDiscount() {

        // given
        BigDecimal totalAmount = new BigDecimal(100);
        BigDecimal minimumAmount = new BigDecimal(30);
        Discount discount = new Coupon(totalAmount, minimumAmount);

        // when
        BigDecimal discountAmount = couponStrategy.calculateDiscountAmount(totalAmount, discount);

        // then
        assertEquals(totalAmount, discountAmount);

    }

    @Test
    void givenCampaignStrategy_shouldCalculateDiscountWithZero() {

        // given
        BigDecimal totalAmount = new BigDecimal(30);
        BigDecimal minimumAmount = new BigDecimal(100);
        Discount discount = new Coupon(minimumAmount, minimumAmount);

        // when
        BigDecimal discountAmount = couponStrategy.calculateDiscountAmount(totalAmount, discount);

        // then
        assertEquals(BigDecimal.ZERO, discountAmount);

    }
}
