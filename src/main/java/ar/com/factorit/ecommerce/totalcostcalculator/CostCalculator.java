package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface CostCalculator {
    BigDecimal getTotalCost(List<CartItemDto> items);
}
