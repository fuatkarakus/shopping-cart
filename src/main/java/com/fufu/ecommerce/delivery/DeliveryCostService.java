package com.fufu.ecommerce.delivery;

import java.math.BigDecimal;

/**
 * An interface that holds delivery cost logic
 */
public interface DeliveryCostService {

    BigDecimal calculate( Integer deliveryCount, Integer productCount );

}
