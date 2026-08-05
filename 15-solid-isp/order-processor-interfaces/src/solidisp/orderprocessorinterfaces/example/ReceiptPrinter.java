package solidisp.orderprocessorinterfaces.example;

/** AFTER: implements only Printable — the one role it actually plays. */
public class ReceiptPrinter implements OrderProcessorRoles.Printable {
    public String printInvoice(Order order) {
        StringBuilder sb = new StringBuilder("Receipt for " + order.getCustomerEmail() + ":\n");
        for (OrderItem item : order.getItems()) {
            sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
        }
        return sb.toString();
    }
}
