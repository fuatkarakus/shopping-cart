package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.discount.Campaign;
import com.fufu.ecommerce.domain.discount.Discount;

import java.math.BigDecimal;

public class CampaignStrategy implements DiscountStrategy{

    @Override
    public BigDecimal calculateDiscountAmount(BigDecimal totalAmount, Discount discount) {
        Campaign campaign = (Campaign) discount;
        BigDecimal discountAmount = BigDecimal.valueOf(totalAmount
                .multiply(campaign.getRate())
                .divide(BigDecimal.valueOf(100)).doubleValue());

        if ( totalAmount.compareTo(discountAmount) >= 0 )
            return discountAmount;

        return BigDecimal.ZERO;
    }
}
