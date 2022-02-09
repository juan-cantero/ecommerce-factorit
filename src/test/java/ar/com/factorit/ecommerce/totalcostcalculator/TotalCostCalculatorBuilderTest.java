package ar.com.factorit.ecommerce.totalcostcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

class TotalCostCalculatorBuilderTest {


    @Nested
    public class GetTotalCostCalculator {

        @Test
        public void WhenVipClientIsTrue_ShouldBeWithVipClientDiscountInstance() {
            boolean isSpecialCart = false;
            boolean isVipClient = true;
            int totalItems = 2;
            boolean equalsProductPromotionIsActive = false;


            CostCalculator costCalculator =
                    TotalCostCalculatorBuilder.getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            assertThat(costCalculator,instanceOf(WithVipClientDiscount.class));

        }

        @Test
        public void WhenSpecialCartIsTrueAndMoreThan3Items_ShouldBeWithSpecialCartDiscountInstance() {

            boolean isSpecialCart = true;
            boolean isVipClient = false;
            int totalItems = 4;
            boolean equalsProductPromotionIsActive = false;

            CostCalculator costCalculator =
                    TotalCostCalculatorBuilder.getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            assertThat(costCalculator,instanceOf(WithSpecialCartDiscount.class));
        }

        @Test
        public void WhenSpecialCartIsTrueAndMoreThan3Items_ShouldNotBeWithSpecialCartDiscountInstance() {

            boolean isSpecialCart = true;
            boolean isVipClient = false;
            int totalItems = 2;
            boolean equalsProductPromotionIsActive = false;

            CostCalculator costCalculator =
                    TotalCostCalculatorBuilder.getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            assertThat(costCalculator,not(instanceOf(WithSpecialCartDiscount.class)));
        }

        @Test
        public void WhenMoreThan3ItemsAndNotSpecialCart_ShouldBeInstanceOfWithRegularCartDiscount() {
            boolean isSpecialCart = false;
            boolean isVipClient = false;
            int totalItems = 4;
            boolean equalsProductPromotionIsActive = false;

            CostCalculator costCalculator =
                    TotalCostCalculatorBuilder.getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

            assertThat(costCalculator,instanceOf(WithRegularCartDiscount.class));
        }
    }

    @Test
    public void WhenEualsProductPromotionIsActive_ShouldBeInstanceOfWithEqualsProductsDiscount() {
        boolean isSpecialCart = false;
        boolean isVipClient = false;
        int totalItems = 2;
        boolean equalsProductPromotionIsActive = true;

        CostCalculator costCalculator =
                TotalCostCalculatorBuilder.getTotalCostCalculator(isSpecialCart,isVipClient,totalItems,equalsProductPromotionIsActive);

        assertThat(costCalculator,instanceOf(WithEqualsProductsDiscount.class));
    }

}