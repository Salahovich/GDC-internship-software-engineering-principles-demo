package solidsrp.orderresponsibilities.example;

/**
 * BEFORE: violates SRP. OrderManagerSrpViolation has FOUR reasons to
 * change: the pricing rule, the invoice format, how orders are
 * persisted, and how confirmations are sent. A change to any one of
 * them means editing (and re-testing) a class responsible for all four.
 */
public class OrderManagerSrpViolation {
    public double calculateTotal(Order order) {
        return order.getSubtotal();
    }

    public String printInvoice(Order order) {
        StringBuilder sb = new StringBuilder("Invoice for " + order.getCustomerEmail() + ":\n");
        for (OrderItem item : order.getItems()) {
            sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
        }
        sb.append("Total: $").append(calculateTotal(order));
        return sb.toString();
    }

    public void saveToDatabase(Order order) {
        System.out.println("[DB] saved order for " + order.getCustomerEmail());
    }

    public void sendConfirmationEmail(Order order) {
        System.out.println("[email -> " + order.getCustomerEmail() + "] Your order is confirmed!");
    }
}
