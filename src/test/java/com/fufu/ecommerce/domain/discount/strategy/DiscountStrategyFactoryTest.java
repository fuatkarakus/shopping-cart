package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.discount.Campaign;
import com.fufu.ecommerce.domain.discount.Coupon;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

class DiscountStrategyFactoryTest {

    BigDecimal rate = new BigDecimal(50);
    Category tech = new Category("tech");

    @Test
    void givenRateType_shouldReturnRateStrategy() {

        //given
        Campaign campaign = new Campaign(rate, tech);

        // when
        DiscountStrategy strategy =  DiscountStrategyFactory.getStrategyByDiscountType(campaign);

        // then
        assertThat(strategy, Matchers.instanceOf(CampaignStrategy.class));
    }

    @Test
    void givenAmountType_shouldReturnAmountStrategy() {

        //given
        Coupon coupon = new Coupon(rate, rate);

        // when
        DiscountStrategy strategy =  DiscountStrategyFactory.getStrategyByDiscountType(coupon);

        // then
        assertThat(strategy, Matchers.instanceOf(CouponStrategy.class));
    }
}
