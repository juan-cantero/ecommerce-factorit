package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public class WithVipClientDiscount extends CostCalculatorDecorator{
    public WithVipClientDiscount(CostCalculator costCalculator) {
        super(costCalculator);
    }

    @Override
    public BigDecimal getTotalCost(List<CartItemDto> items) {
        BigDecimal totalCost = super.getTotalCost(items);
        // si totalCost es mayor a 2000
        if (totalCost.compareTo(BigDecimal.valueOf(2000)) == 1)
            return totalCost.subtract(BigDecimal.valueOf(500));

        return totalCost;
    }
}
