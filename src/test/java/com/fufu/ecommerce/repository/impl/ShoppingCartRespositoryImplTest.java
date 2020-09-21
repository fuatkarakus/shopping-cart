package com.fufu.ecommerce.repository.impl;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.Product;
import com.fufu.ecommerce.domain.discount.Campaign;
import com.fufu.ecommerce.domain.discount.Coupon;
import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.repository.ShoppingCartRepository;
import com.fufu.ecommerce.shoppingcart.ShoppingCart;
import com.fufu.ecommerce.shoppingcart.ShoppingCartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartRepositoryImplTest {

    ShoppingCart cart = new ShoppingCart();
    ShoppingCartRepository repository;

    Category fruit = new Category( "fruit");
    Category sport = new Category("sport");

    Product redApple = new Product("apple", BigDecimal.ZERO, fruit);
    Product tshirt = new Product("Tshirt", BigDecimal.valueOf(100), sport);

    Discount campaign = new Campaign(BigDecimal.valueOf(20), fruit);
    Discount coupon = new Coupon(BigDecimal.valueOf(20), BigDecimal.valueOf(10));

    @BeforeEach
    void setup() {
        repository = new ShoppingCartRepositoryImpl(cart);
    }

    @Test
    void whenSaveItem_thenSuccess() {
        // given
        ShoppingCartItem entity = new ShoppingCartItem(tshirt);

        //when
        repository.save(entity);

        //then
        assertTrue(repository.existsById(entity));
    }

    @Test
    void whenSaveProduct_thenSuccess() {

        // given
        ShoppingCartItem entity = new ShoppingCartItem(redApple);

        //when
        repository.save(redApple);

        //then
        assertTrue(repository.existsById(entity));
    }

    @Test
    void whenFindItem_thenSuccess() {
        // given
        ShoppingCartItem entity = new ShoppingCartItem(tshirt);

        //when
        repository.save(entity);

        //then
        assertTrue(repository.find(entity).isPresent());
    }

    @Test
    void whenSaveAllItem_thenSuccess() {
        // given
        ShoppingCartItem entity = new ShoppingCartItem(tshirt);
        ShoppingCartItem entity2 = new ShoppingCartItem(redApple);
        List<ShoppingCartItem> items = Arrays.asList(entity, entity2);

        //when
        repository.saveAll(items);

        //then
        assertTrue(repository.find(entity).isPresent());
        assertTrue(repository.find(entity2).isPresent());
        assertEquals(new LinkedHashSet<>(items), repository.findAll());
    }

    @Test
    void whenGetItemsByCategory_thenSuccess() {
        // given
        ShoppingCartItem entity = new ShoppingCartItem(tshirt);
        ShoppingCartItem entity2 = new ShoppingCartItem(redApple);
        List<ShoppingCartItem> items = Arrays.asList(entity, entity2);

        //when
        repository.saveAll(items);

        //then
        assertEquals(1, repository.getItemsByCategory(sport).size());
        assertEquals(2, repository.countCategory());
    }

    @Test
    void whenCountItems_thenSuccess() {
        // given
        ShoppingCartItem entity = new ShoppingCartItem(tshirt, 2);
        ShoppingCartItem entity2 = new ShoppingCartItem(redApple, 5);
        List<ShoppingCartItem> items = Arrays.asList(entity, entity2);

        //when
        repository.saveAll(items);

        //then
        assertEquals(7, repository.count());
    }

    @Test
    void whenDeleteItem_thenSuccess() {

        // given
        ShoppingCartItem entity = new ShoppingCartItem(redApple);
        repository.save(redApple);

        //when
        repository.delete(entity);

        //then
        assertFalse(repository.existsById(entity));
    }

    @Test
    void whenDeleteAllItem_thenClearItems() {

        // given
        repository.save(redApple);

        //when
        repository.deleteAll();

        //then
        assertEquals(0, (int) repository.count());
    }


    @Test
    void whenDeleteProduct_thenDeleteSuccess() {

        // given
        ShoppingCartItem item = new ShoppingCartItem(redApple);
        repository.save(redApple);

        //when
        repository.delete(redApple);

        //then
        assertFalse(repository.existsById(item));
    }

    @Test
    void whenSaveDiscount_thenSaveSuccess() {

        // given
        repository.saveDiscount( campaign );

        //then
        assertTrue(repository.getDiscounts().contains(campaign));
    }

    @Test
    void whenGetCartAmounts_thenReturn() {
        // given
        BigDecimal total = new BigDecimal(100);

        // when
        repository.setTotalDiscount(total);
        repository.setDeliveryCost(total);
        repository.setTotalPrice(total);
        //then
        assertEquals(total, repository.getTotalDiscount() );
        assertEquals(total, repository.getDeliveryCost() );
        assertEquals(total, repository.getTotalPrice() );
    }

    @Test
    void whenSaveExistItem_thenIncrementQuantity() {
        // given
        ShoppingCartItem item = new ShoppingCartItem(tshirt, 10);
        ShoppingCartItem item2 = new ShoppingCartItem(tshirt, 20);

        // when
        repository.save(item);
        repository.save(item2);

        //then
        assertEquals(1 , repository.findAll().size());
    }

    @Test
    void whenSaveProductWithQuantity_thenSuccess() {

        // when
        repository.save(tshirt, 10);

        //then
        assertEquals(10, repository.count());
    }

    @Test
    void whenDeleteDiscount_thenSuccess() {

        // given
        repository.saveDiscount(coupon);

        // when
        repository.delete(coupon);

        // then
        assertFalse(repository.getDiscounts().contains(coupon));
    }

    @Test
    void whenDeleteAllItems_thenSuccess() {

        // given
        ShoppingCartItem entity = new ShoppingCartItem(tshirt);
        ShoppingCartItem entity2 = new ShoppingCartItem(redApple);
        List<ShoppingCartItem> items = Arrays.asList(entity, entity2);
        repository.saveAll(items);

        // when
        repository.deleteAll(items);

        // then
        assertEquals(0, repository.count());
    }

}
