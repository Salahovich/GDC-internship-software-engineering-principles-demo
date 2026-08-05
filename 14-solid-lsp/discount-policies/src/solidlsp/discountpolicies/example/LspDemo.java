package solidlsp.discountpolicies.example;

import solidlsp.discountpolicies.before.RushFeeAsDiscountPolicyBefore;

import solidlsp.discountpolicies.exercise.FreeShippingAsDiscountPolicy;

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

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: a surcharge disguised as a DiscountPolicy breaks trusting code ==");
        System.out.println(PrintSavings.printSavings(order, new DiscountPolicy.VipDiscount()));
        System.out.println(PrintSavings.printSavings(order, new RushFeeAsDiscountPolicyBefore())
                + "  <- nonsensical: negative savings for something that isn't a discount at all");

        System.out.println();
        System.out.println("== AFTER: surcharges get their own honest abstraction ==");
        double subtotal = order.getSubtotal();
        PriceAdjustment vipDiscount = new PriceAdjustment.DiscountAdjustment(new DiscountPolicy.VipDiscount());
        PriceAdjustment rushFee = new PriceAdjustment.RushFeeSurcharge();
        System.out.println("VIP discount applied: $" + vipDiscount.applyTo(subtotal));
        System.out.println("Rush fee applied: $" + rushFee.applyTo(subtotal) + "  <- clearly a surcharge, nothing pretends otherwise");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/FreeShippingAsDiscountPolicy.java ==");
        System.out.println(PrintSavings.printSavings(order, new FreeShippingAsDiscountPolicy())
                + "  <- wrongly claims the whole ORDER was free, when only shipping should be");
    }
}
