package solidlsp.discountpolicies.example;

/**
 * BEFORE: violates LSP. RushFeeAsDiscountPolicy technically implements
 * DiscountPolicy — it compiles, it satisfies the method signature — but
 * a "-20% discount" is really a 20% SURCHARGE. It breaks the unwritten
 * promise every other DiscountPolicy made, and any code (like
 * PrintSavings) that trusted that promise now says something
 * nonsensical.
 */
public class RushFeeAsDiscountPolicyBefore implements DiscountPolicy {
    public double getDiscountPercent() {
        return -20; // a "discount" that actually makes the total bigger
    }
}
