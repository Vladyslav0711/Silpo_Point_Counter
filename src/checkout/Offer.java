package checkout;

import java.time.LocalDate;

public abstract class Offer {
    private LocalDate date;
    public abstract void apply(Check check);
    public Offer(LocalDate date){
        this.date = date;
    }

    public boolean checkTerm(){
        if((LocalDate.now()).compareTo(this.date)<0){
            return true;
        }
        else return false;
    }
}
