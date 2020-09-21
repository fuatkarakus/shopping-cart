package com.fufu.ecommerce.domain.discount;

import com.fufu.ecommerce.domain.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CampaignTest {

    BigDecimal rate = new BigDecimal(50);
    Category fruit = new Category("fruit");

    @Test
    void shouldCreate_thenSuccess() {

        // given
        Campaign campaign = new Campaign(rate, fruit);

        // then
        assertEquals(DiscountType.RATE, campaign.getType());
        assertEquals(rate, campaign.getRate());
    }

    @Test
    void shouldNotCreate_thenFailure() {

        // given
        BigDecimal bigrate = new BigDecimal(200);

        // then
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Campaign(bigrate, fruit);
        });
        assertEquals( "Rate must be in rage of 0 and 100 ", exception.getMessage());
    }


    @Test
    void givenCoupons_shouldEqual() {

        // given
        Campaign campaign = new Campaign(rate, fruit);
        Campaign campaign1 = new Campaign(rate, fruit);

        // then
        assertEquals(campaign, campaign1);
    }
}
