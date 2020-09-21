package com.fufu.ecommerce.shoppingcart.factory;

import com.fufu.ecommerce.delivery.DeliveryCostService;
import com.fufu.ecommerce.delivery.impl.DeliveryCostServiceImpl;
import com.fufu.ecommerce.repository.ShoppingCartRepository;
import com.fufu.ecommerce.repository.impl.ShoppingCartRepositoryImpl;
import com.fufu.ecommerce.service.ShoppingCartService;
import com.fufu.ecommerce.service.impl.ShoppingCartServiceImpl;
import com.fufu.ecommerce.shoppingcart.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartFactoryTest {

    DeliveryCostService service;
    ShoppingCartRepository repository;

    @BeforeEach()
    void setup(){
        ShoppingCart shoppingCart = new ShoppingCart();
        repository = new ShoppingCartRepositoryImpl(shoppingCart);
        service = new DeliveryCostServiceImpl();
    }

    @Test
    void whenCallDefaultInstance_shouldReturnImpl() {
        // when
        ShoppingCartService fromFactory = ShoppingCartFactory.getDefaultInstance();

        // then
        assertTrue(fromFactory instanceof ShoppingCartServiceImpl);
    }

    @Test
    void whenInjectDependencies_shouldReturnImpl() {
        // when
        ShoppingCartService fromFactory = ShoppingCartFactory.getInstance(repository, service);

        // then
        assertTrue(fromFactory instanceof ShoppingCartServiceImpl);
    }



}
