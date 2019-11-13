package checkout;

import java.time.LocalDate;

public class BonusOffer extends Offer{

    public BonusOffer(LocalDate date, Condition condition, Reward reward){
        super(date, condition, reward);
    }
    @Override
    public void chooseOffer(Check check) {
        int points = reward.calcPoints(check);
        check.addPoints(points);
    }
}
