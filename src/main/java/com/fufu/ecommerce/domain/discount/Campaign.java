package com.fufu.ecommerce.domain.discount;

import com.fufu.ecommerce.domain.Category;

import java.math.BigDecimal;
import java.util.Objects;

// exist for product price discounts
// campaign discount vary based on the number of product in the cart which means rate in my opinion
// campaign applicable to a product category
public class Campaign extends Discount{

    private Category category;
    private BigDecimal rate;

    public Campaign(BigDecimal rate, Category category) {
        super(DiscountType.RATE);
        Objects.requireNonNull(rate);
        Objects.requireNonNull(category);
        if (rate.doubleValue() > 100D || rate.doubleValue() < 0D){
            throw new IllegalArgumentException("Rate must be in rage of 0 and 100 ");
        }
        this.category = category;
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return category.equals(campaign.category) &&
                rate.equals(campaign.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, rate);
    }


}
