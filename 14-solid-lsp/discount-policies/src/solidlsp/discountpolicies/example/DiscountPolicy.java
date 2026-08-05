package solidlsp.discountpolicies.example;

/**
 * The abstraction from the OCP demo. Every implementation promises:
 * 0 (no discount) <= result <= 100 (free).
 */
public interface DiscountPolicy {
    double getDiscountPercent();

    class VipDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 10; }
    }
}
