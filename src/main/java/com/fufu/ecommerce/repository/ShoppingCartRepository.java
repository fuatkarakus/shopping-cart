package com.fufu.ecommerce.repository;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.Product;
import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.shoppingcart.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartItem> {

    /**
     * Return items that belong given category
     * @param category
     * @return a {@code List} of cart item
     */
    List<ShoppingCartItem> getItemsByCategory(Category category);

    /**
     * Add discount to shopping cart
     * @param discount
     */
    void saveDiscount(Discount discount);

    /**
     *
     * @return an List of discount
     */
    Set<Discount> getDiscounts();

    /**
     * Add item to cart as {@code Product}
     * @param product
     */
    void save(Product product);

    /**
     * Add product with given quantity
     * @param product
     * @param quantity
     */
    void save(Product product, Integer quantity);

    /**
     * Delete product from cart
     * This clear shopping cart that holds given product
     * @param product
     */
    void delete(Product product);

    /**
     * delete discount from cart
     * @param discount
     */
    void delete(Discount discount);

    /**
     *
     * @return number of distinct category
     */
    Integer countCategory();

    /**
     *
     * @return total price of shopping cart
     */
    BigDecimal getTotalPrice();

    /**
     *
     * @return total delivery cost of shopping cart
     */
    BigDecimal getDeliveryCost();

    /**
     *
     * @return total discount of shopping cart
     */
    BigDecimal getTotalDiscount();

    /**
     * set total price to shopping cart
     */
    void setTotalPrice(BigDecimal totalPrice);

    /**
     * set delivery cost to shopping cart
     */
    void setDeliveryCost(BigDecimal deliveryCost);

    /**
     * set total discount
     */
    void setTotalDiscount(BigDecimal totalDiscount);

    /**
     * to string shopping cart
     */
    String print();
}
