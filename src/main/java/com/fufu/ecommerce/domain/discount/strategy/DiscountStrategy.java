package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.discount.Discount;

import java.math.BigDecimal;

/**
 * An interface that holds discount calculation
 */
public interface DiscountStrategy {

    /**
     * Returns discount amount if discount applicable to given totalAmount
     */
    BigDecimal calculateDiscountAmount(BigDecimal totalAmount, Discount discount);

}
