package com.fufu.ecommerce.service.impl;

import com.fufu.ecommerce.delivery.DeliveryCostService;
import com.fufu.ecommerce.domain.Product;
import com.fufu.ecommerce.domain.discount.Campaign;
import com.fufu.ecommerce.domain.discount.Coupon;
import com.fufu.ecommerce.domain.discount.Discount;
import com.fufu.ecommerce.domain.discount.strategy.DiscountStrategy;
import com.fufu.ecommerce.domain.discount.strategy.DiscountStrategyFactory;
import com.fufu.ecommerce.repository.ShoppingCartRepository;
import com.fufu.ecommerce.service.ShoppingCartService;
import com.fufu.ecommerce.shoppingcart.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A class that handle Business Logic of Shopping Cart
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository repository;
    private final DeliveryCostService deliveryCostService;

    public ShoppingCartServiceImpl(ShoppingCartRepository repository, DeliveryCostService deliveryCostService) {
        this.repository = repository;
        this.deliveryCostService = deliveryCostService;
    }

    @Override
    public void addItem(final ShoppingCartItem item) {
       repository.save(item);
    }

    @Override
    public void addItem(final Product product) {
        repository.save(product);
    }

    @Override
    public void addItem(final Product product, Integer quantity) {
        repository.save(product, quantity);
    }

    public Optional<ShoppingCartItem> findItem(ShoppingCartItem item) {
        return repository.find(item);
    }

    public Integer countItem() {
        return repository.count();
    }

    public void delete(final ShoppingCartItem item) {
        repository.delete(item);
    }

    public void deleteAll() {
      repository.deleteAll();
    }

    public void addDiscount(Discount discount) {
        repository.saveDiscount(discount);
    }

    @Override
    public BigDecimal applyDiscounts() {

        BigDecimal totalAmountOfCart = calculateTotalAmountOfProduct();

        BigDecimal totalDiscount = repository.getTotalDiscount();

        for (Discount discount : repository.getDiscounts()) {

            DiscountStrategy discountStrategy = DiscountStrategyFactory.getStrategyByDiscountType(discount);
            BigDecimal discountAmount = BigDecimal.ZERO;
            if (discount instanceof Campaign) {
                Campaign campaign = (Campaign) discount;
                BigDecimal totalAmountOfProduct = totalAmountOfCampaignProducts(campaign);
                discountAmount = discountStrategy.calculateDiscountAmount(totalAmountOfProduct, campaign);
            } else if (discount instanceof Coupon) {
                Coupon coupon = (Coupon) discount;
                discountAmount = discountStrategy.calculateDiscountAmount(totalAmountOfCart, coupon);
            }

            totalDiscount = totalDiscount.add(discountAmount);
            totalAmountOfCart = totalAmountOfCart.subtract(totalDiscount);

            if ( ! discountAmount.equals(BigDecimal.ZERO) )
                repository.getDiscounts().remove(discount);

            if (totalAmountOfCart.compareTo(BigDecimal.ZERO) < 0)
                break;

        }

        repository.setTotalDiscount(totalDiscount);

        return repository.getTotalDiscount();

    }

    private BigDecimal totalAmountOfCampaignProducts(Campaign campaign) {
        List<ShoppingCartItem> itemsByCategory = repository.getItemsByCategory(campaign.getCategory());
        return totalAmountGivenItems(itemsByCategory);
    }

    private BigDecimal totalAmountGivenItems(List<ShoppingCartItem> items) {
        return items
                .stream()
                .map(ShoppingCartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Campaign> getCampaigns(List<Discount> discounts){
        return discounts.stream()
                .filter(Campaign.class::isInstance)
                .map(Campaign.class::cast)
                .collect(Collectors.toList());
    }

    private List<Coupon> getCoupons(List<Discount> discounts){
        return discounts.stream()
                .filter(Coupon.class::isInstance)
                .map(Coupon.class::cast)
                .collect(Collectors.toList());
    }

    public Integer countCategory() {
        return this.repository.countCategory();
    }

    @Override
    public BigDecimal calculateDeliveryCost() {
        BigDecimal deliveryCost = this.deliveryCostService.calculate( countCategory(), countItem() );
        repository.setDeliveryCost(deliveryCost);
        return deliveryCost;
    }

    @Override
    public BigDecimal calculateTotalAmountOfCart() {
       return repository.getTotalPrice()
               .subtract(repository.getTotalDiscount())
               .add(repository.getDeliveryCost());
    }

    @Override
    public BigDecimal calculateTotalAmountOfProduct() {
        BigDecimal totalPrice = repository
                .findAll()
                .stream()
                .map(ShoppingCartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        repository.setTotalPrice(totalPrice);
        return totalPrice;
    }

    @Override
    public String print() {
        return repository.print();
    }

}
