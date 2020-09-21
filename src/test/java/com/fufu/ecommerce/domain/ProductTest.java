package com.fufu.ecommerce.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    Category fruit = new Category("fruit");

    @Test
    void givenNullTitle_shouldThrowNullException() {

        // given
        String title = null;

        // then
        assertThrows(NullPointerException.class, () -> {
            new Product(title, BigDecimal.ZERO, null);
        });
    }

    @Test
    void givenNullPrice_shouldThrowNullException() {

        // given
        String title = "apple";

        // then
        assertThrows(NullPointerException.class, () -> {
            new Product(title, null, fruit);
        });
    }

    @Test
    void givenProducts_shouldEqual() {
        // given
        Product blueApple = new Product("apple", BigDecimal.ZERO, fruit);
        Product redApple = new Product("apple", BigDecimal.ZERO, fruit);

        // then
        assertEquals(Boolean.TRUE, blueApple.equals(redApple));
    }

}
