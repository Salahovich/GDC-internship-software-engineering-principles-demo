package solidlsp.discountpolicies.exercise;

import solidlsp.discountpolicies.example.DiscountPolicy;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * FreeShippingAsDiscountPolicy below has the same problem
 * RushFeeAsDiscountPolicyBefore had: it implements DiscountPolicy and
 * returns 100 (meaning "free") even though it's really about waiving
 * shipping, not the item price — plug it into PrintSavings.printSavings()
 * and it wrongly claims the ENTIRE order was free.
 *
 * Task:
 *   a) Create a ShippingWaiver interface (or reuse PriceAdjustment from
 *      the example package) that represents "shipping is free" honestly,
 *      separate from DiscountPolicy.
 *   b) Update LspDemo.main() to apply free shipping as its own concept,
 *      not as a DiscountPolicy.
 *   c) Delete this class once nothing calls it.
 */
public class FreeShippingAsDiscountPolicy implements DiscountPolicy {
    public double getDiscountPercent() {
        return 100; // "free" — but this is a shipping waiver, not an item discount
    }
}
