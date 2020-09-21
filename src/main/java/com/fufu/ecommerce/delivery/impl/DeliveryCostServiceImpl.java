package com.fufu.ecommerce.delivery.impl;

import com.fufu.ecommerce.delivery.DeliveryCostService;

import java.math.BigDecimal;

public class DeliveryCostServiceImpl implements DeliveryCostService {

    @Override
    public BigDecimal calculate(Integer deliveryCount, Integer productCount) {
        return BigDecimal.valueOf(deliveryCount.longValue() * productCount.longValue());
    }

}
