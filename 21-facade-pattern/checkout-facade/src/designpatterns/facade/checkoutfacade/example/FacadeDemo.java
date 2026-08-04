package designpatterns.facade.checkoutfacade.example;

/**
 * FACADE PATTERN
 * ----------------------------------------------------------------------
 * Gives client code one simple entry point in front of several
 * subsystems, so callers don't need to know each subsystem or the
 * correct order to call them in.
 */
public class FacadeDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: ManualCheckout — caller must know 3 subsystems and their order ==");
        new ManualCheckout().placeOrderTheHardWay("amina@example.com", "MUG-01", 1, 12.0);
        System.out.println("^ The card gets charged before we even know if the item is in stock.");

        System.out.println();
        System.out.println("== AFTER: CheckoutFacade.placeOrder() — one call, correct order guaranteed ==");
        new CheckoutFacade().placeOrder("amina@example.com", "MUG-01", 1, 12.0);
        System.out.println("^ Same three subsystems underneath, but the facade always checks stock first.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/RefundFacadeTodo.java ==");
    }
}
