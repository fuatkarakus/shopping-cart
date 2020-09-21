package com.fufu.ecommerce.delivery.impl;

import com.fufu.ecommerce.delivery.DeliveryCostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class DeliveryCostServiceImplTest {

    DeliveryCostService deliveryCostService;

    @BeforeEach
    void setup() {
        deliveryCostService = new DeliveryCostServiceImpl();
    }

    @Test
    void givenProductAndDeliveryNumber_shouldReturnExactCost() {

        // given
        Integer productNumber = 4;
        Integer deliveryNumber = 6;

        // when
        BigDecimal cost =  deliveryCostService.calculate(deliveryNumber, productNumber);

        //then
        assertThat(cost).isEqualTo(BigDecimal.valueOf(4*6));

    }

}
