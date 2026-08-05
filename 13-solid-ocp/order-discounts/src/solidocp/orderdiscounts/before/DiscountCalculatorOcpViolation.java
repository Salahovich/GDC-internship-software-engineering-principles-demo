package solidocp.orderdiscounts.before;

import solidocp.orderdiscounts.example.Order;

/**
 * BEFORE: violates OCP. DiscountCalculatorOcpViolation has to be
 * MODIFIED every time a new customer type is added. The class is never
 * "done" — it's permanently open to editing.
 */
public class DiscountCalculatorOcpViolation {
    public double calculateTotal(Order order, String customerType) {
        double subtotal = order.getSubtotal();
        double discountPercent;
        if (customerType.equals("REGULAR")) {
            discountPercent = 0;
        } else if (customerType.equals("VIP")) {
            discountPercent = 10;
        } else if (customerType.equals("STUDENT")) {
            discountPercent = 15;
        } else {
            throw new IllegalArgumentException("unknown customer type: " + customerType);
        }
        return subtotal * (1 - discountPercent / 100);
    }
}
