package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public class CostCalculatorDecorator implements CostCalculator{

    private CostCalculator costCalculator;

    public CostCalculatorDecorator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    @Override
    public BigDecimal getTotalCost(List<CartItemDto> items) {
        return costCalculator.getTotalCost(items);
    }
}
