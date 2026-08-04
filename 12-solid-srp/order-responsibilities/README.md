# SOLID (S) — Order Responsibilities

**File:** `src/solidsrp/orderresponsibilities/SrpDemo.java`

## What it shows

Processing an order — calculate, print, save, notify — two ways.

1. **BEFORE (`OrderManagerSrpViolation`)** — one class does all four
   things. It has four reasons to change: the pricing rule, the invoice
   format, how orders are persisted, and how confirmations are sent.
2. **AFTER (`OrderCalculator`, `InvoicePrinter`, `OrderRepository`,
   `OrderNotifier`)** — four classes, each with exactly one reason to
   change. `checkoutOrder()` just orchestrates them.

This demo introduces the `Order`/`OrderItem` entities and the
`OrderNotifier` class that the rest of the SOLID series (13-16) builds on
— `OrderNotifier` in particular gets revisited in
[`16-solid-dip/`](../../16-solid-dip/order-notifications/README.md).

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`OrderExportManagerSrpViolation` (bottom of the file, marked `TODO`)
bundles exporting an order to JSON and writing an audit log entry — two
unrelated reasons to change.

**Task:** split it into `OrderJsonExporter.toJson(order)` and
`OrderAuditLogger.logAccess(order)`. Update `main` to call the two new
classes directly, then delete `OrderExportManagerSrpViolation`.

<details>
<summary>Solution</summary>

```java
static class OrderJsonExporter {
    String toJson(Order order) {
        return "{\"customer\":\"" + order.getCustomerEmail() + "\",\"total\":" + order.getSubtotal() + "}";
    }
}

static class OrderAuditLogger {
    void logAccess(Order order) {
        System.out.println("[AUDIT] order for " + order.getCustomerEmail() + " was exported");
    }
}

// in main():
OrderJsonExporter exporter = new OrderJsonExporter();
OrderAuditLogger logger = new OrderAuditLogger();
System.out.println(exporter.toJson(order));
logger.logAccess(order);
```

</details>
