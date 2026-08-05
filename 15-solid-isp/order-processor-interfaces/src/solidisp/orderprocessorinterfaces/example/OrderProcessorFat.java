package solidisp.orderprocessorinterfaces.example;

/**
 * BEFORE: violates ISP. OrderProcessor bundles four unrelated
 * operations. A class that only wants to print a receipt —
 * SimpleReceiptPrinter — still has to implement calculateTotal(),
 * sendConfirmation(), and refund(), with nothing sensible to put in
 * them.
 */
public interface OrderProcessorFat {
    double calculateTotal(Order order);
    String printInvoice(Order order);
    void sendConfirmation(Order order);
    void refund(Order order, double amount);

    class SimpleReceiptPrinter implements OrderProcessorFat {
        public double calculateTotal(Order order) {
            throw new UnsupportedOperationException("SimpleReceiptPrinter doesn't calculate totals");
        }

        public String printInvoice(Order order) {
            StringBuilder sb = new StringBuilder("Receipt for " + order.getCustomerEmail() + ":\n");
            for (OrderItem item : order.getItems()) {
                sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
            }
            return sb.toString();
        }

        public void sendConfirmation(Order order) {
            throw new UnsupportedOperationException("SimpleReceiptPrinter doesn't send confirmations");
        }

        public void refund(Order order, double amount) {
            throw new UnsupportedOperationException("SimpleReceiptPrinter doesn't process refunds");
        }
    }
}
