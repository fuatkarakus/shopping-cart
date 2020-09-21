package com.fufu.ecommerce.domain.discount;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTest {

    BigDecimal amount = new BigDecimal(100);
    BigDecimal minimumAmount = new BigDecimal(100);

    @Test
    void shouldCreate_thenSuccess() {

        // given
        Coupon coupon = new Coupon(amount, minimumAmount);

        // then
        assertEquals(DiscountType.AMOUNT, coupon.getType());
        assertEquals(amount, coupon.getAmount());
        assertEquals(minimumAmount, coupon.getMinimumAmount());
    }


    @Test
    void shouldNotCreate_thenFailure() {

        // given
        BigDecimal amount = new BigDecimal(100);
        BigDecimal minimumAmount = new BigDecimal(200);


        // then
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coupon(amount, minimumAmount);
        });
        assertEquals( "Minimum Amount cannot bigger than Discount Amount", exception.getMessage());
    }

    @Test
    void givenCoupons_shouldEqual() {

        // given
        Coupon coupon = new Coupon(amount, minimumAmount);
        Coupon coupon1 = new Coupon(amount, minimumAmount);

        // then
        assertEquals(coupon, coupon1);
    }

}
