package solidsrp.orderresponsibilities.example;

/** AFTER: SRP applied — one reason to change: how confirmations are sent. */
public class OrderNotifier {
    public void sendConfirmation(Order order) {
        System.out.println("[email -> " + order.getCustomerEmail() + "] Your order is confirmed!");
    }
}
