package checkout;

public class ByCategory implements Condition {
    @Override
    public boolean isAllowed(Check check) {
        return true;
    }
}
