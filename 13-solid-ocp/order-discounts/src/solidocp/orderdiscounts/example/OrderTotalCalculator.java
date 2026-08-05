package solidocp.orderdiscounts.example;

/** AFTER: never needs to change again — new customer types are new DiscountPolicy classes. */
public class OrderTotalCalculator {
    public double calculateTotal(Order order, DiscountPolicy policy) {
        double subtotal = order.getSubtotal();
        return subtotal * (1 - policy.getDiscountPercent() / 100);
    }
}
