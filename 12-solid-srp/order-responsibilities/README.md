# SOLID (S) — Order Responsibilities

**Files:** `src/solidsrp/orderresponsibilities/`

| Package | File | Role |
|---|---|---|
| `example/` | `OrderItem.java`, `Order.java` | Shared entities |
| `before/` | `OrderManagerSrpViolation.java` | BEFORE — four reasons to change, one class |
| `example/` | `OrderCalculator.java`, `InvoicePrinter.java`, `OrderRepository.java`, `OrderNotifier.java` | AFTER — one reason each |
| `example/` | `OrderCheckout.java` | AFTER — orchestrates the four pieces |
| `example/` | `SrpDemo.java` | `main()` — runs everything |
| `exercise/` | `OrderExportManagerSrpViolation.java` | TODO exercise — given SRP-violating class |

## What it shows

Processing an order — calculate, print, save, notify — two ways.

1. **BEFORE (`OrderManagerSrpViolation`)** — one class does all four
   things. It has four reasons to change: the pricing rule, the invoice
   format, how orders are persisted, and how confirmations are sent.
2. **AFTER (`OrderCalculator`, `InvoicePrinter`, `OrderRepository`,
   `OrderNotifier`)** — four classes, each with exactly one reason to
   change. `OrderCheckout.checkoutOrder()` just orchestrates them.

This demo introduces the `Order`/`OrderItem` entities and the
`OrderNotifier` class that the rest of the SOLID series (13-16) builds on
— `OrderNotifier` in particular gets revisited in
[`16-solid-dip/`](../../16-solid-dip/order-notifications/README.md).

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`OrderExportManagerSrpViolation` (see
`exercise/OrderExportManagerSrpViolation.java`) bundles exporting an
order to JSON and writing an audit log entry — two unrelated reasons to
change.

**Task:** split it into `OrderJsonExporter.toJson(order)` and
`OrderAuditLogger.logAccess(order)` — each in its own file. Update
`SrpDemo.main` to call the two new classes directly, then delete
`OrderExportManagerSrpViolation`.

<details>
<summary>Solution</summary>

```java
public class OrderJsonExporter {
    public String toJson(Order order) {
        return "{\"customer\":\"" + order.getCustomerEmail() + "\",\"total\":" + order.getSubtotal() + "}";
    }
}

public class OrderAuditLogger {
    public void logAccess(Order order) {
        System.out.println("[AUDIT] order for " + order.getCustomerEmail() + " was exported");
    }
}
```

</details>
