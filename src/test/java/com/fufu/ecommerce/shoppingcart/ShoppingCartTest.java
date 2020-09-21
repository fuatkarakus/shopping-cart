package com.fufu.ecommerce.shoppingcart;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ShoppingCartTest {

    @Test
    void whenCreateNewCart_shouldBeClear() {
        // when
        ShoppingCart cart = new ShoppingCart();

        // then
        assertEquals(BigDecimal.ZERO, cart.getDeliveryCost());
        assertEquals(BigDecimal.ZERO, cart.getTotalPrice());
        assertEquals(BigDecimal.ZERO, cart.getTotalDiscount());
        assertNotNull(cart.getCartItems());
        assertNotNull(cart.getDiscounts());
    }
}
