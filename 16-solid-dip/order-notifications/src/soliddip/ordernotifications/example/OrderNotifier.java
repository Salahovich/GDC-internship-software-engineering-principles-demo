package soliddip.ordernotifications.example;

/**
 * AFTER: DIP applied. OrderNotifier (high-level) only knows about
 * NotificationChannel — never EmailSender or SmsSender directly.
 */
public class OrderNotifier {
    private final NotificationChannel channel;

    public OrderNotifier(NotificationChannel channel) {
        this.channel = channel;
    }

    public void notifyCustomer(Order order) {
        channel.send(order.getCustomerEmail(), "Your order is confirmed! Total: $" + order.getSubtotal());
    }
}
