package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public class WithSpecialCartDiscount extends CostCalculatorDecorator{
    public WithSpecialCartDiscount(CostCalculator costCalculator) {
        super(costCalculator);
    }

    @Override
    public BigDecimal getTotalCost(List<CartItemDto> items) {
        return super.getTotalCost(items).subtract(BigDecimal.valueOf(150));
    }
}
