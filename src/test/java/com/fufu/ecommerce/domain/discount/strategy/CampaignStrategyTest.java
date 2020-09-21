package com.fufu.ecommerce.domain.discount.strategy;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.discount.Campaign;
import com.fufu.ecommerce.domain.discount.Discount;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CampaignStrategyTest {

    CampaignStrategy campaignStrategy = new CampaignStrategy();

    @Test
    void givenCampaignStrategy_shouldCalculateDiscount() {

        // given
        BigDecimal totalAmount = new BigDecimal(100);
        BigDecimal minimumAmount = new BigDecimal(30);
        Discount discount = new Campaign(minimumAmount, new Category("fruit"));

        // when
        BigDecimal discountAmount = campaignStrategy.calculateDiscountAmount(totalAmount, discount);

        // then
        assertThat(minimumAmount,  Matchers.comparesEqualTo(discountAmount));

    }

    @Test
    void givenCampaignStrategy_shouldCalculateDiscountExact() {

        // given
        BigDecimal totalAmount = new BigDecimal(29);
        BigDecimal discountRate = new BigDecimal(99);
        Discount discount = new Campaign(discountRate, new Category("fruit"));

        // when
        BigDecimal discountAmount = campaignStrategy.calculateDiscountAmount(totalAmount, discount);

        // then
        assertEquals(BigDecimal.valueOf(28.71), discountAmount);

    }

}
