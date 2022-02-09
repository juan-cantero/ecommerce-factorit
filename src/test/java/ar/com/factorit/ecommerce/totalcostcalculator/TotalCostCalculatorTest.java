package ar.com.factorit.ecommerce.totalcostcalculator;

import ar.com.factorit.ecommerce.cart.CartItemDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static ar.com.factorit.ecommerce.mockdata.ProductDataMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TotalCostCalculatorTest {


    // En todos los casos de prueba el carrito tiene activada la promocion del 4x3

    @Nested
    public class GetTotalCost {

        @Test
        public void Add_WithoutAnyPromotion_ReturnSumOfPrices() {
            boolean isSpecialCart = false;
            boolean isVipClient = false;
            int totalItems = 4;
            boolean equalsProductPromotionIsActive = true;

            CostCalculator costCalculator = TotalCostCalculatorBuilder
                    .getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);


            List<CartItemDto> items = List.of(
                    new CartItemDto(1,4, getCocaCola())

            ) ;

            BigDecimal total = costCalculator.getTotalCost(items);

            BigDecimal expected = BigDecimal.valueOf(350);

            assertThat(total,equalTo(expected ));

        }

        @Test
        public void Add_WithVipClientDiscount_ReturnSumOfPricesAndApplyDiscount() {
            boolean isSpecialCart = false;
            boolean isVipClient = true;
            int totalItems = 2;
            boolean equalsProductPromotionIsActive = true;

            CostCalculator costCalculator = TotalCostCalculatorBuilder
                    .getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            List<CartItemDto> items = List.of(
                    new CartItemDto(1,1, getSmartTv()),
                    new CartItemDto(2,1,getPcGamer())
            ) ;

            BigDecimal total = costCalculator.getTotalCost(items);

            BigDecimal expected = BigDecimal.valueOf(239500);

            assertThat(total,equalTo(expected ));

        }

        @Test
        public void Add_withSpecialCartDiscount_ReturnSumOfProductsAndApplyDiscount() {
            boolean isSpecialCart = true;
            boolean isVipClient = false;
            int totalItems = 4;
            boolean equalsProductPromotionIsActive = true;

            CostCalculator costCalculator = TotalCostCalculatorBuilder
                    .getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            List<CartItemDto> items = List.of(
                    new CartItemDto(1,2, getSmartTv()),
                    new CartItemDto(2,2,getCocaCola())
            ) ;


            BigDecimal total = costCalculator.getTotalCost(items);

            BigDecimal expected = BigDecimal.valueOf(80150);

            assertThat(total,equalTo(expected ));

        }

        @Test
        public void Add_withRegularCartDiscount_ReturnSumOfProductsAndApplyDiscount() {
            boolean isSpecialCart = false;
            boolean isVipClient = false;
            int totalItems = 4;
            boolean equalsProductPromotionIsActive = true;

            CostCalculator costCalculator = TotalCostCalculatorBuilder
                    .getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            List<CartItemDto> items = List.of(
                    new CartItemDto(1,2, getSmartTv()),
                    new CartItemDto(2,2,getCocaCola())
            ) ;


            BigDecimal total = costCalculator.getTotalCost(items);

            BigDecimal expected = BigDecimal.valueOf(80200);

            assertThat(total,equalTo(expected ));

        }

        @Test
        public void Add_withEqualsProductsDiscountAndRegularCartDiscount_ReturnSumOfProductsAndApplyDiscount() {
            boolean isSpecialCart = false;
            boolean isVipClient = false;
            int totalItems = 4;
            boolean equalsProductPromotionIsActive = true;

            CostCalculator costCalculator = TotalCostCalculatorBuilder
                    .getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            List<CartItemDto> items = List.of(
                    new CartItemDto(2,4,getCocaCola())
            ) ;


            BigDecimal total = costCalculator.getTotalCost(items);

            BigDecimal expected = BigDecimal.valueOf(350);

            assertThat(total,equalTo(expected ));

        }











    }

}