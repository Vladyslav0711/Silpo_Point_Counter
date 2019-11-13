package checkout;

public class Percent implements DiscountRules {
    private int discount;
    public Percent(int discount){
        this.discount = discount;
    }

    @Override
    public int calcDiscount(Check check){
        return discount;
    }

}
