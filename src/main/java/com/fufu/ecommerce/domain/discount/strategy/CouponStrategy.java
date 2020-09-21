package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.discount.Coupon;
import com.fufu.ecommerce.domain.discount.Discount;

import java.math.BigDecimal;

public class CouponStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculateDiscountAmount(BigDecimal totalAmount, Discount discount) {
        Coupon coupon = (Coupon) discount;
        if(  (totalAmount.compareTo(coupon.getAmount()) >= 0))
            return coupon.getAmount();

        return BigDecimal.ZERO;
    }

}
