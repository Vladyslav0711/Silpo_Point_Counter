package checkout;

import java.time.LocalDate;

public abstract class Offer {
    private LocalDate date;
    private Condition condition;
    protected Reward reward;
    protected DiscountRules discountRules;

    public void apply(Check check) {
        condition.isAllowed(check);
        if (checkTerm()) {
            if (condition.isAllowed(check)) {
                chooseOffer(check);
            }
        }
    }

    public abstract void chooseOffer(Check check);

    public Offer(LocalDate date, Condition condition, Reward reward) {
        this.date = date;
        this.condition = condition;
        this.reward = reward;
    }
    public Offer(LocalDate date, Condition condition, DiscountRules discountRules) {
        this.date = date;
        this.condition = condition;
        this.discountRules = discountRules;
    }


    private boolean checkTerm() {
        return LocalDate.now().isBefore(this.date);
    }
}
