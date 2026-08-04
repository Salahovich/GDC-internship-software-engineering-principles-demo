# Adapter — Payment Adapter

**Files:** `src/designpatterns/adapter/paymentadapter/`

| Package | File | Role |
|---|---|---|
| `example/` | `PaymentProcessor.java` | The abstraction checkout code depends on |
| `example/` | `ModernPaymentGateway.java` | Already compatible |
| `example/` | `LegacyPaymentGateway.java` | BEFORE — incompatible legacy class (different method, different unit) |
| `example/` | `LegacyPaymentGatewayAdapter.java` | AFTER — adapts legacy to `PaymentProcessor` |
| `example/` | `AdapterDemo.java` | `main()` — runs everything |
| `exercise/` | `LegacyInventorySystem.java` | Given legacy class for the exercise |
| `exercise/` | `InventoryLookupAdapterTodo.java` | TODO exercise instructions |

## What it shows

Charging a customer through two different payment gateways.

1. **BEFORE (`LegacyPaymentGateway`)** — a different method name
   (`submitPaymentInCents`) and a different unit (cents, not dollars).
   Checkout code written against `PaymentProcessor.pay(double)` can't
   call it directly.
2. **AFTER (`LegacyPaymentGatewayAdapter`)** — implements
   `PaymentProcessor`, converts dollars to cents, and delegates to the
   legacy gateway. Checkout code loops over a `List<PaymentProcessor>`
   and calls `pay(...)` the same way for the modern gateway and the
   adapted legacy one — it never needs to know the legacy one exists.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`LegacyInventorySystem` (see `exercise/LegacyInventorySystem.java`) has
the same problem: its method is named `checkQuantityOnHand`, not
`getStock`.

**Task:** write the `InventoryLookup` interface and a
`LegacyInventorySystemAdapter implements InventoryLookup` that wraps
`LegacyInventorySystem` and delegates `getStock(sku)` to
`checkQuantityOnHand(sku)` — the same shape as
`LegacyPaymentGatewayAdapter`.

<details>
<summary>Solution</summary>

```java
public interface InventoryLookup {
    int getStock(String sku);
}

public class LegacyInventorySystemAdapter implements InventoryLookup {
    private final LegacyInventorySystem legacySystem;

    public LegacyInventorySystemAdapter(LegacyInventorySystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    public int getStock(String sku) {
        return legacySystem.checkQuantityOnHand(sku);
    }
}
```

</details>
