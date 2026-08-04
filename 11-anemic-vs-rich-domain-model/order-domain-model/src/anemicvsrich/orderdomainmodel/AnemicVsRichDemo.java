package anemicvsrich.orderdomainmodel;

import java.util.ArrayList;
import java.util.List;

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
 * follows Tell, Don't Ask. See this file's README for a fuller
 * discussion of why "anemic" isn't a competing principle, just the name
 * for what happens when you don't apply Tell, Don't Ask to your domain
 * layer.
 *
 * This demo builds an order's total two ways: BEFORE, an anemic
 * OrderAnemic plus an external OrderServiceAnemic that owns the discount
 * rule — except OrderAnemic's own public setter lets any other code
 * bypass that rule entirely; AFTER, a rich Order that enforces the rule
 * itself, with no setter left to bypass.
 */
public class AnemicVsRichDemo {

    // ======================================================================
    // 1) BEFORE — anemic domain model.
    //    OrderAnemic is just a bag of fields. The rule "discount must be
    //    0-100" lives in OrderServiceAnemic, not in OrderAnemic — so it
    //    only applies if every caller remembers to go through the
    //    service. Any code with a reference to the order can call
    //    setDiscountPercent() directly and skip the rule completely.
    // ======================================================================

    static class OrderAnemic {
        private final List<Double> itemPrices = new ArrayList<>();
        private double discountPercent;

        List<Double> getItemPrices() {
            return itemPrices;
        }

        void addItemPrice(double price) {
            itemPrices.add(price);
        }

        double getDiscountPercent() {
            return discountPercent;
        }

        void setDiscountPercent(double discountPercent) {
            this.discountPercent = discountPercent; // no validation — it's just a bag of fields
        }
    }

    static class OrderServiceAnemic {
        static void applyDiscount(OrderAnemic order, double percent) {
            // The rule lives HERE, outside the object it applies to.
            if (percent < 0 || percent > 100) {
                throw new IllegalArgumentException("discount must be between 0 and 100");
            }
            order.setDiscountPercent(percent);
        }

        static double calculateTotal(OrderAnemic order) {
            double subtotal = order.getItemPrices().stream().mapToDouble(Double::doubleValue).sum();
            return subtotal * (1 - order.getDiscountPercent() / 100);
        }
    }

    // ======================================================================
    // 2) AFTER — rich domain model.
    //    Order enforces its own rule inside applyDiscount(). There is no
    //    setDiscountPercent() to bypass — the only way to change the
    //    discount is through the method that validates it.
    // ======================================================================

    static class Order {
        private final List<Double> itemPrices = new ArrayList<>();
        private double discountPercent;

        void addItem(double price) {
            if (price < 0) {
                throw new IllegalArgumentException("price must not be negative");
            }
            itemPrices.add(price);
        }

        void applyDiscount(double percent) {
            if (percent < 0 || percent > 100) {
                throw new IllegalArgumentException("discount must be between 0 and 100");
            }
            this.discountPercent = percent;
        }

        double getTotal() {
            double subtotal = itemPrices.stream().mapToDouble(Double::doubleValue).sum();
            return subtotal * (1 - discountPercent / 100);
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    AccountAnemic below has the same problem: AccountServiceAnemic
    //    owns the "can't withdraw more than the balance" rule, but
    //    AccountAnemic's own setBalance() lets any other code bypass it.
    //
    //    Your task:
    //      a) Create a rich Account class with a private balance field
    //         and a withdraw(double amount) method that checks the
    //         balance and throws IllegalStateException if amount is too
    //         large — no public setBalance().
    //      b) Update main() to build an Account and call
    //         account.withdraw(amount) directly instead of
    //         AccountServiceAnemic.withdraw(account, amount).
    //      c) Delete AccountAnemic and AccountServiceAnemic once nothing
    //         calls them.
    // ======================================================================

    static class AccountAnemic {
        private double balance;

        AccountAnemic(double balance) {
            this.balance = balance;
        }

        double getBalance() {
            return balance;
        }

        void setBalance(double balance) {
            this.balance = balance; // no validation — it's just a bag of fields
        }
    }

    static class AccountServiceAnemic {
        static void withdraw(AccountAnemic account, double amount) {
            if (amount > account.getBalance()) {
                throw new IllegalStateException("insufficient balance");
            }
            account.setBalance(account.getBalance() - amount);
        }
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: the discount rule lives outside OrderAnemic, and can be bypassed ==");
        OrderAnemic anemicOrder = new OrderAnemic();
        anemicOrder.addItemPrice(100.0);
        OrderServiceAnemic.applyDiscount(anemicOrder, 10); // goes through the service — rule enforced
        System.out.println("Total after service-applied 10% discount: $" + OrderServiceAnemic.calculateTotal(anemicOrder));

        anemicOrder.setDiscountPercent(500); // bypasses the service entirely — nothing stops this
        System.out.println("Total after directly setting a 500% discount: $" + OrderServiceAnemic.calculateTotal(anemicOrder)
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
        System.out.println("== TODO exercise: make Account rich the same way ==");
        AccountAnemic account = new AccountAnemic(50.0);
        AccountServiceAnemic.withdraw(account, 20);
        System.out.println("Balance after withdraw(20) via service: $" + account.getBalance());
        account.setBalance(999.0); // bypasses the service entirely — nothing stops this either
        System.out.println("Balance after directly setting it to 999: $" + account.getBalance() + "  <- bypassed the rule");
    }
}
