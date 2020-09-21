package com.fufu.ecommerce.service;

import com.fufu.ecommerce.domain.Product;
import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.shoppingcart.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Function API of Shopping Cart Business Logic
 */
public interface ShoppingCartService {

    /**
     * Add new item to cart
     * @param product
     */
    void addItem(final ShoppingCartItem product);

    /**
     * Add new item as product to cart
     * @param product
     */
    void addItem(final Product product);

    /**
     * Add new item as product with given quantity to cart
     * @param product
     * @param quantity
     */
    void addItem(final Product product, Integer quantity);

    /**
     * Find item on cart as {@code Optional}
     * @param item
     * @return Optional item in cart
     */
    Optional<ShoppingCartItem> findItem(ShoppingCartItem item);

    /**
     * Total product count at cart
     * @return product count
     */
    Integer countItem();

    /**
     * Delete item in cart
     * @param item
     */
    void delete(final ShoppingCartItem item);

    /**
     * Clear cart
     */
    void deleteAll();

    /**
     * Add discount to cart
     * @param discount
     */
    void addDiscount(final Discount discount);

    /**
     * Apply existing discounts to cart
     * @return discount amount
     */
    BigDecimal applyDiscounts();

    /**
     * Calculation delivery cost for current cart
     * @return delivery cost
     */
    BigDecimal calculateDeliveryCost();

    /**
     *
     * @return Total amount after added discounts and delivery cost
     */
    BigDecimal calculateTotalAmountOfCart();

    /**
     *
     * @return Total amount of products in the shopping cart
     */
    BigDecimal calculateTotalAmountOfProduct();


    String print();
}
