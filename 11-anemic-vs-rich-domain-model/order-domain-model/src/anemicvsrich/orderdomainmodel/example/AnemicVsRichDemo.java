package anemicvsrich.orderdomainmodel.example;

import anemicvsrich.orderdomainmodel.before.OrderAnemic;

import anemicvsrich.orderdomainmodel.exercise.AccountAnemic;

/**
 * ANEMIC vs. RICH DOMAIN MODEL
 * ----------------------------------------------------------------------
 * An ANEMIC domain object is a plain data holder: getters and setters,
 * no rules. All the actual business logic lives outside it, in a
 * "service" class that pulls the data out, decides something, and pushes
 * a new value back in. A RICH domain object owns its own rules: you tell
 * it what you want done, and it enforces its own invariants — there is
 * no public setter for a caller to bypass.
 *
 * Note: this is the SAME distinction the Tell, Don't Ask demo covered —
 * an anemic model is what you get at the whole-class level when nothing
 * follows Tell, Don't Ask. See this demo's README for a fuller
 * discussion of why "anemic" isn't a competing principle, just the name
 * for what happens when you don't apply Tell, Don't Ask to your domain
 * layer.
 */
public class AnemicVsRichDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: the discount rule lives outside OrderAnemic, and can be bypassed ==");
        OrderAnemic anemicOrder = new OrderAnemic();
        anemicOrder.addItemPrice(100.0);
        OrderAnemic.OrderServiceAnemic.applyDiscount(anemicOrder, 10); // goes through the service — rule enforced
        System.out.println("Total after service-applied 10% discount: $" + OrderAnemic.OrderServiceAnemic.calculateTotal(anemicOrder));

        anemicOrder.setDiscountPercent(500); // bypasses the service entirely — nothing stops this
        System.out.println("Total after directly setting a 500% discount: $" + OrderAnemic.OrderServiceAnemic.calculateTotal(anemicOrder)
                + "  <- nonsensical, and OrderAnemic itself allowed it");

        System.out.println();
        System.out.println("== AFTER: Order enforces its own rule — there's no setter left to bypass ==");
        Order richOrder = new Order();
        richOrder.addItem(100.0);
        richOrder.applyDiscount(10);
        System.out.println("Total after applyDiscount(10): $" + richOrder.getTotal());
        try {
            richOrder.applyDiscount(500);
        } catch (IllegalArgumentException e) {
            System.out.println("applyDiscount(500) rejected by the order itself: " + e.getMessage());
        }
        System.out.println("Total unchanged: $" + richOrder.getTotal());

        System.out.println();
        System.out.println("== TODO exercise: see exercise/AccountAnemic.java ==");
        AccountAnemic account = new AccountAnemic(50.0);
        AccountAnemic.AccountServiceAnemic.withdraw(account, 20);
        System.out.println("Balance after withdraw(20) via service: $" + account.getBalance());
        account.setBalance(999.0); // bypasses the service entirely — nothing stops this either
        System.out.println("Balance after directly setting it to 999: $" + account.getBalance() + "  <- bypassed the rule");
    }
}
