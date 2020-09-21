package com.fufu.ecommerce.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class that represent Products
 */
public class Product {
    private String title;
    private BigDecimal price;
    private Category category;

    public Product(String title, BigDecimal price, Category category) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(price, "Product should have a price");
        Objects.requireNonNull(category);
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        Product product = (Product) o;
        return title.equals(product.title) &&
                price.equals(product.price) &&
                category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, category);
    }
}
