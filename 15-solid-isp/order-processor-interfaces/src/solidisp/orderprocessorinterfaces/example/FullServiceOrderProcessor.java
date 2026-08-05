package solidisp.orderprocessorinterfaces.example;

/** AFTER: a class CAN still implement all four roles when it genuinely needs to. */
public class FullServiceOrderProcessor implements
        OrderProcessorRoles.TotalCalculable, OrderProcessorRoles.Printable,
        OrderProcessorRoles.Notifiable, OrderProcessorRoles.Refundable {

    public double calculateTotal(Order order) {
        return order.getSubtotal();
    }

    public String printInvoice(Order order) {
        return "Invoice for " + order.getCustomerEmail() + ": $" + calculateTotal(order);
    }

    public void sendConfirmation(Order order) {
        System.out.println("[email -> " + order.getCustomerEmail() + "] Order confirmed!");
    }

    public void refund(Order order, double amount) {
        System.out.println("[refund] $" + amount + " refunded to " + order.getCustomerEmail());
    }
}
