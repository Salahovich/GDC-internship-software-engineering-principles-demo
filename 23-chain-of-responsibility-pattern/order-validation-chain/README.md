# Chain of Responsibility — Order Validation Chain

**Files:** `src/designpatterns/cor/ordervalidationchain/`

| Package | File | Role |
|---|---|---|
| `example/` | `Order.java` | Shared entity |
| `example/` | `OrderValidatorMonolithic.java` | BEFORE — one method, every rule |
| `example/` | `OrderValidationHandler.java` | AFTER — abstract base handler |
| `example/` | `EmailValidationHandler.java`, `StockAvailabilityHandler.java` | AFTER — one rule each |
| `example/` | `ChainOfResponsibilityDemo.java` | `main()` — runs everything |
| `exercise/` | `FraudCheckHandlerTodo.java` | TODO exercise instructions |

## What it shows

Validating an order against two rules (email, stock), two ways.

1. **BEFORE (`OrderValidatorMonolithic`)** — one method with an if/else
   for every rule. Adding a rule means editing this method; testing one
   rule means running all of them.
2. **AFTER (`OrderValidationHandler` chain)** — each rule is its own
   handler, linked with `setNext()`. `chain.handle(order)` walks the
   chain and stops at the first failure.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Risk wants a third rule added: reject orders with more than 10 items
(see `exercise/FraudCheckHandlerTodo.java` for the full brief).

**Task:** write `FraudCheckHandler extends OrderValidationHandler` and
chain it after the existing two handlers. You should not need to touch
`EmailValidationHandler` or `StockAvailabilityHandler` to do this.

<details>
<summary>Solution</summary>

```java
public class FraudCheckHandler extends OrderValidationHandler {
    @Override
    protected boolean check(Order order) {
        if (order.getItems().size() > 10) {
            System.out.println("[FraudCheckHandler] Rejected: too many items");
            return false;
        }
        return true;
    }
}

// in main():
OrderValidationHandler chain = new EmailValidationHandler();
chain.setNext(new StockAvailabilityHandler())
        .setNext(new FraudCheckHandler());
```

</details>
