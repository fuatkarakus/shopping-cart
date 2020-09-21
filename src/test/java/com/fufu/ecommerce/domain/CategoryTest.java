package com.fufu.ecommerce.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @Test
    void givenNullTitle_shouldThrowNullException() {

        // given
        String title = null;

        // then
        assertThrows(NullPointerException.class, () -> {
            new Category(title);
        });
    }

    @Test
    void givenCategories_shouldEqual() {

        // given
        Category blueApple = new Category( "fruit");
        Category redApple = new Category("fruit");

        // then
        assertEquals(blueApple, redApple);
    }

    @Test
    void givenNull_shouldThrowNullException() {

        // then
        assertThrows(NullPointerException.class, () -> {
            new Category(null, null, null);
        });
    }

    @Test
    void givenNullParent_shouldThrowNullException() {

        // then
        assertThrows(NullPointerException.class, () -> {
            new Category(null, null);
        });
    }

    @Test
    void givenParentAndChild_shouldGet() {
        Category blueApple = new Category( "fruit");
        Category redApple =  new Category(blueApple, "malatya",null);

        assertEquals(blueApple, redApple.getParent());
    }

    @Test
    void givenParent_shouldEqualToParent() {
        Category blueApple = new Category( "fruit");
        Category redApple =  new Category(blueApple, "malatya");

        assertEquals(blueApple, redApple.getParent());
    }

}
