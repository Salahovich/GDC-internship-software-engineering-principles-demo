package soliddip.ordernotifications;

import java.util.List;

/**
 * SOLID — D: DEPENDENCY INVERSION PRINCIPLE (DIP)
 * ----------------------------------------------------------------------
 * "High-level modules should not depend on low-level modules — both
 * should depend on abstractions." The order-confirmation notifier
 * introduced back in the SRP demo (the first SOLID letter) is the
 * perfect example: it's a high-level policy ("tell the customer their
 * order is confirmed") that has no business knowing HOW that message
 * gets delivered.
 *
 * This is the last demo in the SOLID series, closing the loop: BEFORE,
 * OrderNotifier is hard-wired to one concrete EmailSender, so adding SMS
 * means editing the notifier itself; AFTER, OrderNotifier depends only
 * on a NotificationChannel abstraction, and new channels are added
 * without touching it at all.
 */
public class DipDemo {

    // ======================================================================
    // Shared entities — same as the earlier SOLID demos.
    // ======================================================================

    static class OrderItem {
        private final String name;
        private final double price;
        private final int qty;

        OrderItem(String name, double price, int qty) {
            this.name = name;
            this.price = price;
            this.qty = qty;
        }

        double getLineTotal() { return price * qty; }
    }

    static class Order {
        private final String customerEmail;
        private final List<OrderItem> items;

        Order(String customerEmail, List<OrderItem> items) {
            this.customerEmail = customerEmail;
            this.items = items;
        }

        String getCustomerEmail() { return customerEmail; }

        double getSubtotal() {
            return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
        }
    }

    // ======================================================================
    // 1) BEFORE — violates DIP.
    //    OrderNotifierDipViolation (high-level: "notify the customer")
    //    directly constructs and calls EmailSender (low-level: "how to
    //    send an email"). To add SMS notifications, you have to open up
    //    and edit the high-level notifier itself.
    // ======================================================================

    static class EmailSender {
        void send(String recipient, String message) {
            System.out.println("[EMAIL -> " + recipient + "] " + message);
        }
    }

    static class OrderNotifierDipViolation {
        private final EmailSender emailSender = new EmailSender(); // hard-wired to one concrete channel

        void notifyCustomer(Order order) {
            emailSender.send(order.getCustomerEmail(), "Your order is confirmed! Total: $" + order.getSubtotal());
        }
    }

    // ======================================================================
    // 2) AFTER — DIP applied.
    //    NotificationChannel is the abstraction both sides depend on.
    //    OrderNotifier (high-level) only knows about
    //    NotificationChannel — never EmailSender or SmsSender directly.
    //    Adding a new channel means writing a new class that implements
    //    the interface; the notifier itself never changes.
    // ======================================================================

    interface NotificationChannel {
        void send(String recipient, String message);
    }

    static class EmailChannel implements NotificationChannel {
        public void send(String recipient, String message) {
            System.out.println("[EMAIL -> " + recipient + "] " + message);
        }
    }

    static class SmsChannel implements NotificationChannel {
        public void send(String recipient, String message) {
            System.out.println("[SMS -> " + recipient + "] " + message);
        }
    }

    static class OrderNotifier {
        private final NotificationChannel channel;

        OrderNotifier(NotificationChannel channel) {
            this.channel = channel;
        }

        void notifyCustomer(Order order) {
            channel.send(order.getCustomerEmail(), "Your order is confirmed! Total: $" + order.getSubtotal());
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    OrderRepositoryDipViolation below has the same problem: it
    //    directly constructs a concrete MySqlDatabase — the high-level
    //    "save this order" policy is welded to one specific database.
    //
    //    Your task:
    //      a) Create a Database interface with a save(String data)
    //         method.
    //      b) Make MySqlDatabase implement it, and add a second
    //         implementation, InMemoryDatabase, that just stores strings
    //         in a List (handy for tests).
    //      c) Create an OrderRepository that takes a Database in its
    //         constructor and delegates save() to it.
    //      d) Update main() to build an OrderRepository with each
    //         Database implementation, then delete
    //         OrderRepositoryDipViolation.
    // ======================================================================

    static class MySqlDatabase {
        void save(String data) {
            System.out.println("[MySQL] saved: " + data);
        }
    }

    static class OrderRepositoryDipViolation {
        private final MySqlDatabase database = new MySqlDatabase(); // hard-wired to one concrete database

        void save(Order order) {
            database.save("order for " + order.getCustomerEmail());
        }
    }

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: OrderNotifier is welded to EmailSender ==");
        OrderNotifierDipViolation oldNotifier = new OrderNotifierDipViolation();
        oldNotifier.notifyCustomer(order);
        System.out.println("^ Adding SMS support means editing OrderNotifierDipViolation itself.");

        System.out.println();
        System.out.println("== AFTER: OrderNotifier depends only on the NotificationChannel abstraction ==");
        OrderNotifier emailNotifier = new OrderNotifier(new EmailChannel());
        OrderNotifier smsNotifier = new OrderNotifier(new SmsChannel());
        emailNotifier.notifyCustomer(order);
        smsNotifier.notifyCustomer(order);
        System.out.println("^ SMS support was added without changing OrderNotifier at all —");
        System.out.println("  that's the whole SOLID series closing the loop.");

        System.out.println();
        System.out.println("== TODO exercise: fix OrderRepositoryDipViolation below ==");
        OrderRepositoryDipViolation oldRepo = new OrderRepositoryDipViolation();
        oldRepo.save(order);
    }
}
