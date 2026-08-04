# Facade — Checkout Facade

**Files:** `src/designpatterns/facade/checkoutfacade/`

| Package | File | Role |
|---|---|---|
| `example/` | `InventoryService.java` | Subsystem — stock |
| `example/` | `PaymentService.java` | Subsystem — charging |
| `example/` | `NotificationService.java` | Subsystem — customer emails |
| `example/` | `ManualCheckout.java` | BEFORE — client orchestrates all three itself |
| `example/` | `CheckoutFacade.java` | AFTER — one method, correct order enforced |
| `example/` | `FacadeDemo.java` | `main()` — runs everything |
| `exercise/` | `RefundFacadeTodo.java` | TODO exercise instructions |

## What it shows

Checking out an order that touches three subsystems, two ways.

1. **BEFORE (`ManualCheckout`)** — the caller sequences the three
   subsystem calls itself, and charges the card *before* checking stock.
   If the item is out of stock, the customer has already been charged.
2. **AFTER (`CheckoutFacade`)** — one `placeOrder()` method checks stock
   first, so a stock failure happens before any money moves. Callers
   never see the individual subsystems, so they can't get the order
   wrong.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Support wants a refund flow, which touches the same three subsystems
(see `exercise/RefundFacadeTodo.java` for the full brief).

**Task:** add a `refund(...)` method to `CheckoutFacade` that gives the
money back, restocks the item, and notifies the customer — in that
order, the same way `placeOrder` sequences its three steps.

<details>
<summary>Solution</summary>

```java
// added to PaymentService:
public void refund(String customerEmail, double amount) {
    System.out.println("[PaymentService] Refunded " + customerEmail + " $" + amount);
}

// added to CheckoutFacade:
public void refund(String customerEmail, String sku, int quantity, double amount) {
    payment.refund(customerEmail, amount);
    inventory.reserve(sku, -quantity);
    notifications.sendOrderConfirmation(customerEmail);
}
```

</details>
