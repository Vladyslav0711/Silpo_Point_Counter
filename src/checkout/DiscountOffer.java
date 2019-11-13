package checkout;

import java.time.LocalDate;

public class DiscountOffer extends Offer {
    public DiscountOffer(LocalDate date, Condition condition, DiscountRules discountRules){
        super(date, condition, discountRules);
    }
    @Override
    public void chooseOffer(Check check) {
        int totalCost = discountRules.calcDiscount(check);
        check.getTotalCost(totalCost);
    }
}
