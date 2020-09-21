package com.fufu.ecommerce.shoppingcart;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ShoppingCartItem {

    private final Product product;
    private final Category category;
    private final BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;

    public ShoppingCartItem(Product product, Integer quantity) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(quantity);
        this.product = product;
        this.category =  product.getCategory();
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice();
    }

    public ShoppingCartItem(Product product) {
        Objects.requireNonNull(product);
        this.product = product;
        this.category =  product.getCategory();
        this.unitPrice = product.getPrice();
        this.quantity = 1;
        this.totalPrice = calculateTotalPrice();
    }

    public BigDecimal calculateTotalPrice() {
        return getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return product.equals(that.product) &&
                category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, category);
    }


}
