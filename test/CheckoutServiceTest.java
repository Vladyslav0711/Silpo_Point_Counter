import checkout.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private LocalDate dayBefore;
    private LocalDate dayAfter;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
        dayBefore = LocalDate.now().minusDays(1);
        dayAfter= LocalDate.now().plusDays(1);
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer__addOfferPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addOffer(new BonusOffer(dayAfter, new TotalCost(6), new Flat(2)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }
    @Test
    void useOffer__beforeClosingCheck(){
        checkoutService.addProduct(bred_3);
        checkoutService.addOffer(new BonusOffer(dayAfter, new TotalCost(6), new Flat(2)));
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);
        checkoutService.addOffer(new BonusOffer(dayAfter, new TotalCost(6), new Flat(2)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }
    @Test
    void check__offerTerm__whenOverdue(){
        checkoutService.addProduct(milk_7);
       // checkoutService.addOffer(new AnyGoodsOffer(6, 2,dayBefore));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(7));
    }
    @Test
    void check__offerTerm__whenFresh(){
        checkoutService.addProduct(milk_7);
        checkoutService.addOffer(new BonusOffer(dayAfter, new TotalCost(6), new Flat(2)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(9));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addOffer(new BonusOffer(dayAfter, new ByCategory(), new Factor(2, Category.MILK)));
        //checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, dayAfter));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer_DiscountOffer(){
        checkoutService.addProduct(milk_7);
        checkoutService.addOffer(new DiscountOffer(dayAfter, new ByCategory(), new Percent(50)));
        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalCost(50), is(4));
    }

//    @Test
//    void useOffer_productDiscount(){
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(bred_3);
//
//       // checkoutService.addOffer(new ProductDiscountOffer(milk_7), dayAfter);
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(3));
//    }
}
