package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;
    public AnyGoodsOffer(int totalCost, int points, LocalDate date) {
        super(date);
        this.totalCost = totalCost;
        this.points = points;
    }

    @Override
    public void apply(Check check) {
        if (this.totalCost <= check.getTotalCost())
            check.addPoints(this.points);
    }


}
