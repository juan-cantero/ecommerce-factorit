package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;
import ar.com.factorit.ecommerce.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class TotalCostCalculator implements CostCalculator{


    @Override
    public BigDecimal getTotalCost(List<CartItemDto> items) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (CartItemDto item: items) {
            totalCost = totalCost.add
                                (item.getProduct().getPrice()
                                        .multiply(BigDecimal.valueOf(item.getQuantity())));

        }
        return totalCost;
    }
}
