package solidlsp.discountpolicies.example;

/** Shared calculator — same as the OCP demo. */
public class OrderTotalCalculator {
    public double calculateTotal(Order order, DiscountPolicy policy) {
        return order.getSubtotal() * (1 - policy.getDiscountPercent() / 100);
    }
}
