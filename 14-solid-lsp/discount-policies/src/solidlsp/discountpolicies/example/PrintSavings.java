package solidlsp.discountpolicies.example;

/**
 * Client code written against the DiscountPolicy CONTRACT, not just its
 * signature: it trusts that a "discount" never makes the total go UP,
 * so savings is always zero or positive.
 */
public class PrintSavings {
    public static String printSavings(Order order, DiscountPolicy policy) {
        OrderTotalCalculator calculator = new OrderTotalCalculator();
        double savings = order.getSubtotal() - calculator.calculateTotal(order, policy);
        return "You saved $" + savings + "!";
    }
}
