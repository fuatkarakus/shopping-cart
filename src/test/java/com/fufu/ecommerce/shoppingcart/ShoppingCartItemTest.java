package com.fufu.ecommerce.shoppingcart;

import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShoppingCartItemTest {

    Category category = new Category("school");
    Product product =  new Product("pen", BigDecimal.valueOf(10), category);

    @Test
    void whenCreateItem_thenSuccess() {

        // when
        ShoppingCartItem item = new ShoppingCartItem(product, 2);

        // then
        assertEquals(product.getPrice(), item.getUnitPrice());
        assertEquals(BigDecimal.valueOf(20), item.getTotalPrice());
        assertEquals(BigDecimal.valueOf(20), item.getTotalPrice());
    }

    @Test
    void whenCreateItemWithoutQuantity_thenSuccess() {

        // when
        ShoppingCartItem item = new ShoppingCartItem(product);

        // then
        assertEquals(1 , item.getQuantity());

    }

    @Test
    void givenNullCreate_thenFailure() {

        // given
        Product product = null;

        // then
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new ShoppingCartItem(product);
        });
    }

    @Test
    void givenItems_shouldEqual() {
        // given
        ShoppingCartItem item = new ShoppingCartItem(product, 2);
        ShoppingCartItem item2 = new ShoppingCartItem(product, 10);

        // then
        assertEquals(item, item2);
    }

}
