package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;


    public FactorByCategoryOffer(Category category, int factor, LocalDate date) {
        super(date);
        this.category = category;
        this.factor = factor;
    }
    @Override
    public void getPoints(Check check){
        super.points=check.getCostByCategory(this.category)* (this.factor - 1);
    }
    @Override
    public boolean isAllowed(Check check) {
        return true;
    }
        @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));
    }
}
