package com.fufu.ecommerce.repository.impl;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.Product;
import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.repository.ShoppingCartRepository;
import com.fufu.ecommerce.shoppingcart.ShoppingCart;
import com.fufu.ecommerce.shoppingcart.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class that can handle crud operations of Shopping Cart Object
 */
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private final ShoppingCart shoppingCart;

    public ShoppingCartRepositoryImpl(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void save(ShoppingCartItem entity) {
        if (existsById(entity))
            this.shoppingCart
                    .getCartItems()
                    .stream()
                    .filter(i -> i.equals(entity))
                    .findFirst()
                    .ifPresent(i -> i.setQuantity(i.getQuantity() + entity.getQuantity()));

        this.shoppingCart
                .getCartItems()
                .add(entity);
    }

    @Override
    public void saveAll(Collection<ShoppingCartItem> entities) {
        entities.forEach(this::save);
    }

    @Override
    public Optional<ShoppingCartItem> find(ShoppingCartItem shoppingCartItem) {
        return this.shoppingCart
                .getCartItems()
                .stream()
                .filter(i -> i.equals(shoppingCartItem))
                .findFirst();
    }

    @Override
    public boolean existsById(ShoppingCartItem shoppingCartItem) {
        return this.shoppingCart.getCartItems().contains(shoppingCartItem);
    }

    @Override
    public Collection<ShoppingCartItem> findAll() {
        return this.shoppingCart
                .getCartItems();
    }

    @Override
    public List<ShoppingCartItem> getItemsByCategory(Category category) {
        return shoppingCart.getCartItems()
                .stream()
                .filter(i -> i.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public Integer count() {
        return this.shoppingCart
                .getCartItems()
                .stream()
                .map(ShoppingCartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public void delete(ShoppingCartItem entity) {
        if (existsById(entity))
            this.shoppingCart
                .getCartItems()
                .remove(entity);
    }

    @Override
    public void deleteAll(Collection<? extends ShoppingCartItem> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        this.shoppingCart
                .getCartItems()
                .clear();
    }

    @Override
    public void saveDiscount(Discount discount) {
        this.shoppingCart
                .getDiscounts()
                .add(discount);
    }

    @Override
    public Set<Discount> getDiscounts() {
       return this.shoppingCart.getDiscounts();
    }

    @Override
    public void save(Product product) {
        ShoppingCartItem item = new ShoppingCartItem(product);
        this.save(item);
    }

    @Override
    public void save(Product product, Integer quantity) {
        ShoppingCartItem item = new ShoppingCartItem(product, quantity);
        this.save(item);
    }

    @Override
    public void delete(Product product) {
        ShoppingCartItem item = new ShoppingCartItem(product, 1);
        this.delete(item);
    }

    @Override
    public void delete(Discount discount) {
        this.shoppingCart.getDiscounts().remove(discount);
    }

    @Override
    public Integer countCategory() {
        return this.shoppingCart
                .getCartItems()
                .stream()
                .collect(Collectors.groupingBy(ShoppingCartItem::getCategory))
                .size();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.shoppingCart.getTotalPrice();
    }

    @Override
    public BigDecimal getDeliveryCost() {
        return this.shoppingCart.getDeliveryCost();
    }

    @Override
    public BigDecimal getTotalDiscount() {
        return this.shoppingCart.getTotalDiscount();
    }

    @Override
    public void setTotalPrice(BigDecimal totalPrice) {
        this.shoppingCart.setTotalPrice(totalPrice);
    }

    @Override
    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.shoppingCart.setDeliveryCost(deliveryCost);
    }

    @Override
    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.shoppingCart.setTotalDiscount(totalDiscount);
    }

    @Override
    public String print() {
       return this.shoppingCart.toString();
    }
}
