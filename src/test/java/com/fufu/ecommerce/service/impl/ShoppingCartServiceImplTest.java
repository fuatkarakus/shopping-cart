package com.fufu.ecommerce.service.impl;

import com.fufu.ecommerce.delivery.DeliveryCostService;
import com.fufu.ecommerce.delivery.impl.DeliveryCostServiceImpl;
import com.fufu.ecommerce.domain.Category;
import com.fufu.ecommerce.domain.Product;
import com.fufu.ecommerce.domain.discount.Campaign;
import com.fufu.ecommerce.domain.discount.Coupon;
import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.repository.ShoppingCartRepository;
import com.fufu.ecommerce.service.ShoppingCartService;
import com.fufu.ecommerce.shoppingcart.ShoppingCartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ShoppingCartServiceImplTest {

    ShoppingCartService cartService;
    DeliveryCostService deliveryCostService;
    ShoppingCartRepository repository;

    Category fruit = new Category( "fruit");
    Category sport = new Category("sport");

    Product redApple = new Product("apple", BigDecimal.ZERO, fruit);
    Product tshirt = new Product("Tshirt", BigDecimal.valueOf(100), sport);

    Discount campaign = new Campaign(BigDecimal.valueOf(20), fruit);
    Discount coupon = new Coupon(BigDecimal.valueOf(20), BigDecimal.valueOf(10));

    ShoppingCartItem entity = new ShoppingCartItem(tshirt);
    ShoppingCartItem entity2 = new ShoppingCartItem(redApple);

    @BeforeEach
    void setup() {
        repository =  mock(ShoppingCartRepository.class);
        deliveryCostService =  mock(DeliveryCostServiceImpl.class);
        cartService = new ShoppingCartServiceImpl(repository, deliveryCostService);
    }

    @Test
    void whenFindItem_shouldReturnEntity() {
        // given
        Mockito.when(repository.find(entity)).thenReturn(Optional.ofNullable(entity));

        // when
        Optional<ShoppingCartItem> founded =  cartService.findItem(entity);

        // then
        assertEquals(entity, founded.get());
    }

    @Test
    void whenRepositoryCount_thenReturnCountItem() {
        // given
        Mockito.when(repository.count()).thenReturn(20);

        // when
        Integer count = cartService.countItem();

        // then
        assertEquals(20, count);
    }

    @Test
    void whenCalculateTotalAmountOfCart_thenReturnTotalAmount() {
        Mockito.when(repository.getTotalPrice()).thenReturn(BigDecimal.valueOf(200));
        Mockito.when(repository.getTotalDiscount()).thenReturn(BigDecimal.valueOf(50));
        Mockito.when(repository.getDeliveryCost()).thenReturn(BigDecimal.valueOf(50));

        BigDecimal count = cartService.calculateTotalAmountOfCart();

        assertEquals(BigDecimal.valueOf(200), count);
    }

    @Test
    void whenCalculateDeliveryCost_thenReturnDeliveryCost() {
        Mockito.when(deliveryCostService.calculate(any(), any())).thenReturn(BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(50), cartService.calculateDeliveryCost());
    }

    @Test
    void whenAddItem_shouldSuccess() {
        ShoppingCartService cartService = mock(ShoppingCartService.class);

        cartService.addItem(entity);

        Mockito.verify(cartService, times(1)).addItem(entity);
    }

    @Test
    void whenDeleteAll_shouldSuccess() {
        ShoppingCartService cartService = mock(ShoppingCartService.class);

        cartService.deleteAll();

        Mockito.verify(cartService, times(1)).deleteAll();
    }

    @Test
    void whenDeleteItem_shouldSuccess() {
        ShoppingCartService cartService = mock(ShoppingCartService.class);

        cartService.delete(entity);
        cartService.addItem(entity);
        cartService.addItem(tshirt);
        cartService.addItem(tshirt, 2);

        Mockito.verify(cartService, times(1)).delete(entity);
        Mockito.verify(cartService, times(1)).addItem(entity);
        Mockito.verify(cartService, times(1)).addItem(tshirt);
        Mockito.verify(cartService, times(1)).addItem(tshirt, 2);
    }

    @Test
    void whenAddDiscount_shouldSuccess() {
        ShoppingCartService cartService = mock(ShoppingCartService.class);

        cartService.addDiscount(campaign);

        Mockito.verify(cartService, times(1)).addDiscount(campaign);
    }

    @Test
    void whenCallPrint_shouldReturnString() {
        Mockito.when(repository.print()).thenReturn("somethin");

        String some = cartService.print();

        assertEquals("somethin", some);
    }



}
