package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public class WithEqualsProductsDiscount extends CostCalculatorDecorator{

    public WithEqualsProductsDiscount(CostCalculator costCalculator) {
        super(costCalculator);
    }

    @Override
    public BigDecimal getTotalCost(List<CartItemDto> items) {
        BigDecimal discount = BigDecimal.ZERO;

        for (CartItemDto item: items) {
            if (item.getQuantity() == 4) {
                discount = discount.add(item.getProduct().getPrice());
            }
        }

        return super.getTotalCost(items).subtract(discount);
    }
}
