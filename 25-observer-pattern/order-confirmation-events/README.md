# Observer — Order Confirmation Events

**Files:** `src/designpatterns/observer/orderconfirmationevents/`

| Package | File | Role |
|---|---|---|
| `example/` | `Order.java` | Shared entity |
| `example/` | `InventoryUpdater.java`, `EmailSender.java` | The reactions to a confirmed order |
| `example/` | `OrderNotifierHardcoded.java` | BEFORE — hardcodes both by name |
| `example/` | `OrderListener.java` | AFTER — the abstraction reactions implement |
| `example/` | `OrderPublisher.java` | AFTER — the subject, notifies whoever registered |
| `example/` | `ObserverDemo.java` | `main()` — runs everything |
| `exercise/` | `LoyaltyPointsListenerTodo.java` | TODO exercise instructions |

## What it shows

Reacting to a confirmed order — updating inventory, emailing the
customer — two ways.

1. **BEFORE (`OrderNotifierHardcoded`)** — constructs and calls both
   reactions by name. Adding a third (loyalty points, a webhook,
   anything) means editing this class.
2. **AFTER (`OrderPublisher` + `OrderListener`)** — `InventoryUpdater`
   and `EmailSender` both implement `OrderListener` and register
   themselves with `OrderPublisher`. `confirm()` just loops over whoever
   registered — it never names either one of them.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Marketing wants loyalty points awarded automatically on every confirmed
order (see `exercise/LoyaltyPointsListenerTodo.java` for the full
brief).

**Task:** write `LoyaltyPointsListener implements OrderListener` and
register it with `OrderPublisher` in `main`, alongside the other two.
You should not need to touch `OrderPublisher` or either existing
listener to do this.

<details>
<summary>Solution</summary>

```java
public class LoyaltyPointsListener implements OrderListener {
    public void onOrderConfirmed(Order order) {
        int points = (int) (order.getTotal() / 10);
        System.out.println("[Loyalty] +" + points + " points for " + order.getCustomerEmail());
    }
}

// in main():
publisher.addListener(new LoyaltyPointsListener());
```

</details>
