package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.domain.discount.DiscountType;

public class DiscountStrategyFactory {

    private DiscountStrategyFactory() {}

    /**
     * Return discount calculation strategy at runtime
     */
    public static DiscountStrategy getStrategyByDiscountType(Discount discount) {

        if (discount.getType() == DiscountType.RATE )
            return new CampaignStrategy();

        return new CouponStrategy();

    }
}
