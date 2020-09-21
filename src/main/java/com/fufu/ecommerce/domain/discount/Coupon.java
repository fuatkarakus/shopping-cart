package com.fufu.ecommerce.domain.discount;

import java.math.BigDecimal;
import java.util.Objects;

// exist for cart discount
// minimum cart amount
public class Coupon extends Discount{

    private BigDecimal minimumAmount;
    private BigDecimal amount;

    public Coupon(BigDecimal amount, BigDecimal minimumAmount) {
        super(DiscountType.AMOUNT);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(minimumAmount);
        if (minimumAmount.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Minimum Amount cannot bigger than Discount Amount");
        }
        this.amount = amount;
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return minimumAmount.equals(coupon.minimumAmount) &&
                amount.equals(coupon.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimumAmount, amount);
    }

}
