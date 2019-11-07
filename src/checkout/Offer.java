package checkout;

import java.time.LocalDate;

public abstract class Offer {
    private LocalDate date;
    protected int points;
    public void apply(Check check){
        if(checkTerm()){
            if(isAllowed(check)){
                getPoints(check);
                addPoints(points, check);
            }
        }
    }

    public Offer(LocalDate date) {
        this.date = date;
    }
    private void addPoints(int points, Check check){
        check.addPoints(points);
    }
    protected abstract void getPoints(Check check);
    protected abstract boolean isAllowed(Check check);

    private boolean checkTerm() {
        return LocalDate.now().isBefore(this.date);
    }
}
