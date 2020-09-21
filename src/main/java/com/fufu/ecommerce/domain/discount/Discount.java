package com.fufu.ecommerce.domain.discount;

import java.util.Objects;

/**
 * An abstract class that represent discounts
 */
public abstract class Discount {

    private final DiscountType type;

    public Discount(DiscountType type) {
        Objects.requireNonNull(type);
        this.type = type;
    }

    public DiscountType getType() {
        return type;
    }

}
