package checkout;

public class Factor implements Reward {
    private int factor;
    private Category category;
    public Factor(int factor, Category category){
        this.factor=factor;
        this.category = category;
    }
    @Override
    public int calcPoints(Check check) {
        return check.getCostByCategory(this.category)* (this.factor - 1);
    }
}
