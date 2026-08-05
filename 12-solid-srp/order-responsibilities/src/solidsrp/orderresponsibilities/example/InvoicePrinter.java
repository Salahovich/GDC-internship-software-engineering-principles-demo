package solidsrp.orderresponsibilities.example;

/** AFTER: SRP applied — one reason to change: the invoice format. */
public class InvoicePrinter {
    public String printInvoice(Order order, double total) {
        StringBuilder sb = new StringBuilder("Invoice for " + order.getCustomerEmail() + ":\n");
        for (OrderItem item : order.getItems()) {
            sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
        }
        sb.append("Total: $").append(total);
        return sb.toString();
    }
}
