package ar.com.factorit.ecommerce.totalcostcalculator;


public class TotalCostCalculatorBuilder {
    public static CostCalculator getTotalCostCalculator(boolean isSpecialCart,
                                                        boolean isVipClient,
                                                        int totalItems,
                                                        boolean equalsProductPromotionIsActive)  {
        CostCalculator costCalculator = new TotalCostCalculator();



        if (isSpecialCart && totalItems > 3) {
            costCalculator = new WithSpecialCartDiscount(costCalculator);
        }

        if (!isSpecialCart && totalItems > 3) {
            costCalculator = new WithRegularCartDiscount(costCalculator);
        }

        if (isVipClient) {
            costCalculator = new WithVipClientDiscount(costCalculator);
        }

        if(equalsProductPromotionIsActive) {
            costCalculator = new WithEqualsProductsDiscount(costCalculator);
        }

        return costCalculator;


    }
}
