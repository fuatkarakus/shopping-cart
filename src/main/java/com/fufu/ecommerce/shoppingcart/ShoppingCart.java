package com.fufu.ecommerce.shoppingcart;

import com.fufu.ecommerce.domain.discount.Discount;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShoppingCart {

    private Set<ShoppingCartItem> cartItems;
    private BigDecimal totalPrice;
    private BigDecimal deliveryCost;
    private BigDecimal totalDiscount;
    private final Set<Discount> discounts;

    public ShoppingCart() {
        this.totalDiscount = BigDecimal.ZERO;
        this.deliveryCost = BigDecimal.ZERO;
        this.totalPrice = BigDecimal.ZERO;
        this.cartItems = new LinkedHashSet<>();
        this.discounts = new LinkedHashSet<>();
    }

    public Set<ShoppingCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<ShoppingCartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShoppingCart - cartItems: [");

        for (ShoppingCartItem item : cartItems ) {
            sb.append(item.getProduct().getTitle()).append("  ");
        }

        sb.append( " ] , totalPrice : " )
                .append(totalPrice.toString())
                .append( ",  deliveryCost : ")
                .append(deliveryCost.toString())
                .append(",  totalDiscount : ")
                .append(totalDiscount.toString());

        return sb.toString();
    }
}
