package solidlsp.discountpolicies;

import java.util.List;

/**
 * SOLID — L: LISKOV SUBSTITUTION PRINCIPLE (LSP)
 * ----------------------------------------------------------------------
 * "Subtypes must be substitutable for their base type without breaking
 * the correctness of code written against that base type." It's not
 * enough for a subtype to match the method signature — it has to honor
 * the BEHAVIORAL contract every other implementation already promises,
 * or any code trusting that contract can break.
 *
 * Continuing the DiscountPolicy abstraction from the OCP demo: every
 * existing DiscountPolicy returns a value from 0 (no discount) to 100
 * (free) — a REDUCTION. This demo adds a new "discount" that's really a
 * surcharge (a negative reduction). It compiles fine and satisfies the
 * interface... and breaks the first piece of client code that trusts
 * what DiscountPolicy has always meant.
 */
public class LspDemo {

    // ======================================================================
    // Shared entities and DiscountPolicy abstraction — same as the OCP
    // demo. Every DiscountPolicy so far promises: 0 <= discount <= 100.
    // ======================================================================

    static class OrderItem {
        private final String name;
        private final double price;
        private final int qty;

        OrderItem(String name, double price, int qty) {
            this.name = name;
            this.price = price;
            this.qty = qty;
        }

        double getLineTotal() { return price * qty; }
    }

    static class Order {
        private final String customerEmail;
        private final List<OrderItem> items;

        Order(String customerEmail, List<OrderItem> items) {
            this.customerEmail = customerEmail;
            this.items = items;
        }

        double getSubtotal() {
            return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
        }
    }

    /** Every implementation promises: 0 (no discount) <= result <= 100 (free). */
    interface DiscountPolicy {
        double getDiscountPercent();
    }

    static class VipDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 10; }
    }

    static class OrderTotalCalculator {
        double calculateTotal(Order order, DiscountPolicy policy) {
            return order.getSubtotal() * (1 - policy.getDiscountPercent() / 100);
        }
    }

    // Client code written against the DiscountPolicy CONTRACT, not just
    // its signature: it trusts that a "discount" never makes the total
    // go UP, so savings is always zero or positive.
    static String printSavings(Order order, DiscountPolicy policy) {
        OrderTotalCalculator calculator = new OrderTotalCalculator();
        double savings = order.getSubtotal() - calculator.calculateTotal(order, policy);
        return "You saved $" + savings + "!";
    }

    // ======================================================================
    // 1) BEFORE — violates LSP.
    //    RushFeeAsDiscountPolicy technically implements DiscountPolicy —
    //    it compiles, it satisfies the method signature — but a "-20%
    //    discount" is really a 20% SURCHARGE. It breaks the unwritten
    //    promise every other DiscountPolicy made, and any code (like
    //    printSavings) that trusted that promise now says something
    //    nonsensical.
    // ======================================================================

    static class RushFeeAsDiscountPolicy implements DiscountPolicy {
        public double getDiscountPercent() {
            return -20; // a "discount" that actually makes the total bigger
        }
    }

    // ======================================================================
    // 2) AFTER — LSP respected.
    //    A surcharge isn't a kind of discount, no matter how convenient
    //    it is to bolt onto the existing interface — so it gets its own
    //    abstraction instead of pretending to be a DiscountPolicy. Every
    //    DiscountPolicy still means exactly what it always meant, and
    //    printSavings() is safe to trust again.
    // ======================================================================

    interface PriceAdjustment {
        double applyTo(double subtotal);
    }

    static class DiscountAdjustment implements PriceAdjustment {
        private final DiscountPolicy discountPolicy;

        DiscountAdjustment(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
        }

        public double applyTo(double subtotal) {
            return subtotal * (1 - discountPolicy.getDiscountPercent() / 100);
        }
    }

    static class RushFeeSurcharge implements PriceAdjustment {
        public double applyTo(double subtotal) {
            return subtotal * 1.20; // openly a surcharge, not disguised as a discount
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    FreeShippingAsDiscountPolicy below has the same problem: it
    //    implements DiscountPolicy and returns 100 (meaning "free") even
    //    though it's really about waiving shipping, not the item price —
    //    plug it into printSavings() below and it wrongly claims the
    //    ENTIRE order was free.
    //
    //    Your task:
    //      a) Create a ShippingWaiver interface (or reuse
    //         PriceAdjustment from above) that represents "shipping is
    //         free" honestly, separate from DiscountPolicy.
    //      b) Update main() to apply free shipping as its own concept,
    //         not as a DiscountPolicy.
    //      c) Delete FreeShippingAsDiscountPolicy once nothing calls it.
    // ======================================================================

    static class FreeShippingAsDiscountPolicy implements DiscountPolicy {
        public double getDiscountPercent() {
            return 100; // "free" — but this is a shipping waiver, not an item discount
        }
    }

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: a surcharge disguised as a DiscountPolicy breaks trusting code ==");
        System.out.println(printSavings(order, new VipDiscount()));
        System.out.println(printSavings(order, new RushFeeAsDiscountPolicy())
                + "  <- nonsensical: negative savings for something that isn't a discount at all");

        System.out.println();
        System.out.println("== AFTER: surcharges get their own honest abstraction ==");
        double subtotal = order.getSubtotal();
        PriceAdjustment vipDiscount = new DiscountAdjustment(new VipDiscount());
        PriceAdjustment rushFee = new RushFeeSurcharge();
        System.out.println("VIP discount applied: $" + vipDiscount.applyTo(subtotal));
        System.out.println("Rush fee applied: $" + rushFee.applyTo(subtotal) + "  <- clearly a surcharge, nothing pretends otherwise");

        System.out.println();
        System.out.println("== TODO exercise: fix FreeShippingAsDiscountPolicy below ==");
        System.out.println(printSavings(order, new FreeShippingAsDiscountPolicy())
                + "  <- wrongly claims the whole ORDER was free, when only shipping should be");
    }
}
