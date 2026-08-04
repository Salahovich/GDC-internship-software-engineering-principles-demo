package solidisp.orderprocessorinterfaces;

import java.util.List;

/**
 * SOLID — I: INTERFACE SEGREGATION PRINCIPLE (ISP)
 * ----------------------------------------------------------------------
 * "Clients shouldn't be forced to depend on methods they don't use." A
 * fat interface bundling unrelated operations forces every implementer
 * to deal with all of them — usually by throwing an exception for the
 * ones that don't apply, which breaks any caller that trusted the
 * interface's full contract (the same trap the LSP demo covered from the
 * other direction).
 *
 * Continuing the same Order / OrderItem entities: this demo defines a
 * fat OrderProcessor interface (BEFORE) that a simple receipt printer is
 * forced to partially fake its way through, then splits it into small,
 * role-specific interfaces (AFTER) that a class only implements if it
 * actually needs that role.
 */
public class IspDemo {

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

        String getName() { return name; }
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
        List<OrderItem> getItems() { return items; }

        double getSubtotal() {
            return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
        }
    }

    // ======================================================================
    // 1) BEFORE — violates ISP.
    //    OrderProcessor bundles four unrelated operations. A class that
    //    only wants to print a receipt — SimpleReceiptPrinter — still
    //    has to implement calculateTotal(), sendConfirmation(), and
    //    refund(), with nothing sensible to put in them.
    // ======================================================================

    interface OrderProcessor {
        double calculateTotal(Order order);
        String printInvoice(Order order);
        void sendConfirmation(Order order);
        void refund(Order order, double amount);
    }

    static class SimpleReceiptPrinter implements OrderProcessor {
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

    // ======================================================================
    // 2) AFTER — ISP applied.
    //    Four small, role-specific interfaces. ReceiptPrinter now
    //    implements only Printable — the one role it actually plays. A
    //    full-service processor can still implement all four when it
    //    genuinely needs to; ISP doesn't forbid that, it just stops
    //    FORCING it on classes that don't.
    // ======================================================================

    interface TotalCalculable {
        double calculateTotal(Order order);
    }

    interface Printable {
        String printInvoice(Order order);
    }

    interface Notifiable {
        void sendConfirmation(Order order);
    }

    interface Refundable {
        void refund(Order order, double amount);
    }

    static class ReceiptPrinter implements Printable {
        public String printInvoice(Order order) {
            StringBuilder sb = new StringBuilder("Receipt for " + order.getCustomerEmail() + ":\n");
            for (OrderItem item : order.getItems()) {
                sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
            }
            return sb.toString();
        }
    }

    static class FullServiceOrderProcessor implements TotalCalculable, Printable, Notifiable, Refundable {
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

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    CustomerAccountManager below has the same problem:
    //    SupportDashboardView only wants to look up order history, but
    //    is forced to implement updateProfile() and deleteAccount() too.
    //
    //    Your task:
    //      a) Split CustomerAccountManager into ProfileEditable
    //         (updateProfile), OrderHistoryViewable (viewOrderHistory),
    //         and AccountDeletable (deleteAccount).
    //      b) Make a new ReadOnlySupportDashboard implement only
    //         OrderHistoryViewable.
    //      c) Update main() to use it, then delete SupportDashboardView
    //         and CustomerAccountManager.
    // ======================================================================

    interface CustomerAccountManager {
        void updateProfile(String customerEmail, String newName);
        String viewOrderHistory(String customerEmail);
        void deleteAccount(String customerEmail);
    }

    static class SupportDashboardView implements CustomerAccountManager {
        public void updateProfile(String customerEmail, String newName) {
            throw new UnsupportedOperationException("support dashboard is read-only");
        }

        public String viewOrderHistory(String customerEmail) {
            return "3 past orders for " + customerEmail;
        }

        public void deleteAccount(String customerEmail) {
            throw new UnsupportedOperationException("support dashboard is read-only");
        }
    }

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: SimpleReceiptPrinter is forced to fake three methods it can't support ==");
        SimpleReceiptPrinter oldPrinter = new SimpleReceiptPrinter();
        System.out.println(oldPrinter.printInvoice(order));
        try {
            oldPrinter.calculateTotal(order);
        } catch (UnsupportedOperationException e) {
            System.out.println("calculateTotal() crashed: " + e.getMessage());
        }

        System.out.println();
        System.out.println("== AFTER: ReceiptPrinter only implements the one role it plays ==");
        ReceiptPrinter printer = new ReceiptPrinter();
        System.out.println(printer.printInvoice(order));

        FullServiceOrderProcessor fullService = new FullServiceOrderProcessor();
        System.out.println("Full-service total: $" + fullService.calculateTotal(order));
        fullService.sendConfirmation(order);
        fullService.refund(order, 10.0);

        System.out.println();
        System.out.println("== TODO exercise: fix SupportDashboardView below ==");
        SupportDashboardView dashboard = new SupportDashboardView();
        System.out.println(dashboard.viewOrderHistory("amina@example.com"));
        try {
            dashboard.updateProfile("amina@example.com", "Amina K.");
        } catch (UnsupportedOperationException e) {
            System.out.println("updateProfile() crashed: " + e.getMessage());
        }
    }
}
