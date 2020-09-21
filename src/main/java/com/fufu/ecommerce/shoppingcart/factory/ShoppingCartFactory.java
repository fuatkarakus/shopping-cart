package com.fufu.ecommerce.shoppingcart.factory;

import com.fufu.ecommerce.delivery.DeliveryCostService;
import com.fufu.ecommerce.delivery.impl.DeliveryCostServiceImpl;
import com.fufu.ecommerce.repository.ShoppingCartRepository;
import com.fufu.ecommerce.repository.impl.ShoppingCartRepositoryImpl;
import com.fufu.ecommerce.service.ShoppingCartService;
import com.fufu.ecommerce.service.impl.ShoppingCartServiceImpl;
import com.fufu.ecommerce.shoppingcart.ShoppingCart;

/**
 * A class that initialize Shopping Cart
 */
public class ShoppingCartFactory {

    private ShoppingCartFactory() { }

    public static ShoppingCartService getInstance(ShoppingCartRepository repository,
                                                  DeliveryCostService deliveryCostService) {

        return new ShoppingCartServiceImpl(repository, deliveryCostService);
    }

    public static ShoppingCartService getDefaultInstance() {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartRepository repository = new ShoppingCartRepositoryImpl(shoppingCart);
        DeliveryCostService deliveryCostService = new DeliveryCostServiceImpl();
        return new ShoppingCartServiceImpl(repository, deliveryCostService);
    }

}
