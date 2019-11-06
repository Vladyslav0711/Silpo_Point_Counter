package checkout;

import java.util.*;

public class CheckoutService {

    private Check check;
    private List<Offer> offers = new ArrayList<>();

    public void addOffer(Offer offer){
        offers.add(offer);
    }
    public void deleteOffer(Offer offer){
        offers.remove(offer);
    }
    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check closedCheck = check;
        for(Offer offer : offers){
            useOffer(offer);
           // deleteOffer(offer);
        }
        check = null;
        return closedCheck;
    }

    private void useOffer(Offer offer) {
        if (check != null){
            if(offer.checkTerm())
                offer.apply(check);
        }
    }
}


