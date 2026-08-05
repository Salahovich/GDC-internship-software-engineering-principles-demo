package soliddip.ordernotifications.example;

/**
 * BEFORE: violates DIP. OrderNotifierDipViolation (high-level: "notify
 * the customer") directly constructs and calls EmailSender (low-level:
 * "how to send an email"). To add SMS notifications, you have to open
 * up and edit the high-level notifier itself.
 */
public class OrderNotifierDipViolation {

    public static class EmailSender {
        public void send(String recipient, String message) {
            System.out.println("[EMAIL -> " + recipient + "] " + message);
        }
    }

    private final EmailSender emailSender = new EmailSender(); // hard-wired to one concrete channel

    public void notifyCustomer(Order order) {
        emailSender.send(order.getCustomerEmail(), "Your order is confirmed! Total: $" + order.getSubtotal());
    }
}
