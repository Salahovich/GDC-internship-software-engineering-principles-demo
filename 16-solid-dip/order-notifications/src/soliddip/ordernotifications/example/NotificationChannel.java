package soliddip.ordernotifications.example;

/**
 * AFTER: the abstraction both sides depend on. Adding a new channel
 * means writing a new class that implements this interface; the
 * notifier itself never changes.
 */
public interface NotificationChannel {
    void send(String recipient, String message);

    class EmailChannel implements NotificationChannel {
        public void send(String recipient, String message) {
            System.out.println("[EMAIL -> " + recipient + "] " + message);
        }
    }

    class SmsChannel implements NotificationChannel {
        public void send(String recipient, String message) {
            System.out.println("[SMS -> " + recipient + "] " + message);
        }
    }
}
