package solidsrp.orderresponsibilities.exercise;

import solidsrp.orderresponsibilities.example.Order;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * OrderExportManagerSrpViolation below has the same problem
 * OrderManagerSrpViolation had: it bundles exporting an order to JSON
 * AND writing an audit log entry — two unrelated reasons to change
 * (export format vs. audit policy) in one class.
 *
 * Task:
 *   a) Split it into OrderJsonExporter.toJson(order) and
 *      OrderAuditLogger.logAccess(order) — each in its own file.
 *   b) Update SrpDemo.main() to call the two new classes directly
 *      instead of OrderExportManagerSrpViolation.
 *   c) Delete this class once nothing calls it.
 */
public class OrderExportManagerSrpViolation {
    public String exportToJson(Order order) {
        return "{\"customer\":\"" + order.getCustomerEmail() + "\",\"total\":" + order.getSubtotal() + "}";
    }

    public void logAccess(Order order) {
        System.out.println("[AUDIT] order for " + order.getCustomerEmail() + " was exported");
    }
}
