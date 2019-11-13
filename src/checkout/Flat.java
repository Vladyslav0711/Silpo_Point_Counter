package checkout;

public class Flat implements Reward{
    private int points;
    public Flat(int points){
        this.points = points;
    }
    @Override
    public int calcPoints(Check check) {
       return points;
    }
}
