package solidlsp.discountpolicies.example;

/**
 * AFTER: LSP respected. A surcharge isn't a kind of discount, no matter
 * how convenient it is to bolt onto the existing interface — so it gets
 * its own abstraction instead of pretending to be a DiscountPolicy.
 * Every DiscountPolicy still means exactly what it always meant, and
 * PrintSavings is safe to trust again.
 */
public interface PriceAdjustment {
    double applyTo(double subtotal);

    class DiscountAdjustment implements PriceAdjustment {
        private final DiscountPolicy discountPolicy;

        public DiscountAdjustment(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
        }

        public double applyTo(double subtotal) {
            return subtotal * (1 - discountPolicy.getDiscountPercent() / 100);
        }
    }

    class RushFeeSurcharge implements PriceAdjustment {
        public double applyTo(double subtotal) {
            return subtotal * 1.20; // openly a surcharge, not disguised as a discount
        }
    }
}
