package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;
    public final LocalDate date;
    public AnyGoodsOffer(int totalCost, int points, LocalDate date) {
        this.totalCost = totalCost;
        this.points = points;
        this.date = date;
        this.setDate(this.date);
    }

    @Override
    public void setDate(LocalDate date) {
        super.setDate(date);
    }

    @Override
    public void apply(Check check) {
        if (this.totalCost <= check.getTotalCost())
            check.addPoints(this.points);
    }


}
