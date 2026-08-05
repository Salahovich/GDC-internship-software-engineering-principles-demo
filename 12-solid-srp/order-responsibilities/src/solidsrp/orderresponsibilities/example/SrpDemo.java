package solidsrp.orderresponsibilities.example;

import solidsrp.orderresponsibilities.before.OrderManagerSrpViolation;

import solidsrp.orderresponsibilities.exercise.OrderExportManagerSrpViolation;

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
 */
public class SrpDemo {

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
        OrderCheckout.checkoutOrder(order);

        System.out.println();
        System.out.println("== TODO exercise: see exercise/OrderExportManagerSrpViolation.java ==");
        OrderExportManagerSrpViolation exportManager = new OrderExportManagerSrpViolation();
        System.out.println(exportManager.exportToJson(order));
        exportManager.logAccess(order);
    }
}
