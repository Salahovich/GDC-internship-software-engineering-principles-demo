package solidsrp.orderresponsibilities;

import java.util.List;

/**
 * SOLID — S: SINGLE RESPONSIBILITY PRINCIPLE (SRP)
 * ----------------------------------------------------------------------
 * "A class should have only one reason to change." This is the first of
 * five SOLID demos that all share the same Order / OrderItem entities —
 * watch how they get extended, not replaced, as each letter is
 * introduced, ending with Dependency Inversion.
 *
 * A class that bundles unrelated responsibilities has multiple reasons
 * to change: a business-rule change, a formatting change, a persistence
 * change, and a notification change can all force an edit to the SAME
 * class, even though they have nothing to do with each other.
 *
 * This demo processes an order two ways: BEFORE, one class does
 * calculation, printing, persistence, AND notification; AFTER, four
 * classes each own exactly one of those responsibilities.
 */
public class SrpDemo {

    // ======================================================================
    // Shared entities — reused (redeclared) across all five SOLID demos.
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
    // 1) BEFORE — violates SRP.
    //    OrderManagerSrpViolation has FOUR reasons to change: the
    //    pricing rule, the invoice format, how orders are persisted, and
    //    how confirmations are sent. A change to any one of them means
    //    editing (and re-testing) a class responsible for all four.
    // ======================================================================

    static class OrderManagerSrpViolation {
        double calculateTotal(Order order) {
            return order.getSubtotal();
        }

        String printInvoice(Order order) {
            StringBuilder sb = new StringBuilder("Invoice for " + order.getCustomerEmail() + ":\n");
            for (OrderItem item : order.getItems()) {
                sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
            }
            sb.append("Total: $").append(calculateTotal(order));
            return sb.toString();
        }

        void saveToDatabase(Order order) {
            System.out.println("[DB] saved order for " + order.getCustomerEmail());
        }

        void sendConfirmationEmail(Order order) {
            System.out.println("[email -> " + order.getCustomerEmail() + "] Your order is confirmed!");
        }
    }

    // ======================================================================
    // 2) AFTER — SRP applied.
    //    Each class has exactly one reason to change. checkoutOrder()
    //    just orchestrates them — it has no logic of its own to break.
    // ======================================================================

    static class OrderCalculator {
        double calculateTotal(Order order) {
            return order.getSubtotal();
        }
    }

    static class InvoicePrinter {
        String printInvoice(Order order, double total) {
            StringBuilder sb = new StringBuilder("Invoice for " + order.getCustomerEmail() + ":\n");
            for (OrderItem item : order.getItems()) {
                sb.append(" - ").append(item.getName()).append(": $").append(item.getLineTotal()).append("\n");
            }
            sb.append("Total: $").append(total);
            return sb.toString();
        }
    }

    static class OrderRepository {
        void save(Order order) {
            System.out.println("[DB] saved order for " + order.getCustomerEmail());
        }
    }

    static class OrderNotifier {
        void sendConfirmation(Order order) {
            System.out.println("[email -> " + order.getCustomerEmail() + "] Your order is confirmed!");
        }
    }

    static void checkoutOrder(Order order) {
        OrderCalculator calculator = new OrderCalculator();
        InvoicePrinter printer = new InvoicePrinter();
        OrderRepository repository = new OrderRepository();
        OrderNotifier notifier = new OrderNotifier();

        double total = calculator.calculateTotal(order);
        System.out.println(printer.printInvoice(order, total));
        repository.save(order);
        notifier.sendConfirmation(order);
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    OrderExportManagerSrpViolation below has the same problem: it
    //    bundles exporting an order to JSON AND writing an audit log
    //    entry — two unrelated reasons to change (export format vs.
    //    audit policy) in one class.
    //
    //    Your task:
    //      a) Split it into OrderJsonExporter.toJson(order) and
    //         OrderAuditLogger.logAccess(order).
    //      b) Update main() to call the two new classes directly instead
    //         of OrderExportManagerSrpViolation.
    //      c) Delete OrderExportManagerSrpViolation once nothing calls it.
    // ======================================================================

    static class OrderExportManagerSrpViolation {
        String exportToJson(Order order) {
            return "{\"customer\":\"" + order.getCustomerEmail() + "\",\"total\":" + order.getSubtotal() + "}";
        }

        void logAccess(Order order) {
            System.out.println("[AUDIT] order for " + order.getCustomerEmail() + " was exported");
        }
    }

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: one class, four reasons to change ==");
        OrderManagerSrpViolation manager = new OrderManagerSrpViolation();
        System.out.println(manager.printInvoice(order));
        manager.saveToDatabase(order);
        manager.sendConfirmationEmail(order);

        System.out.println();
        System.out.println("== AFTER: four classes, one reason to change each ==");
        checkoutOrder(order);

        System.out.println();
        System.out.println("== TODO exercise: split OrderExportManagerSrpViolation below ==");
        OrderExportManagerSrpViolation exportManager = new OrderExportManagerSrpViolation();
        System.out.println(exportManager.exportToJson(order));
        exportManager.logAccess(order);
    }
}
