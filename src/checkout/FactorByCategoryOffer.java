package checkout;

import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;
    final LocalDate date;



    public FactorByCategoryOffer(Category category, int factor, LocalDate date) {
        this.category = category;
        this.factor = factor;
        this.date = date;
        this.setDate(this.date);
    }

    @Override
    public void setDate(LocalDate date) {
        super.setDate(date);
    }

    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(this.category);
        check.addPoints(points * (this.factor - 1));
    }
}
