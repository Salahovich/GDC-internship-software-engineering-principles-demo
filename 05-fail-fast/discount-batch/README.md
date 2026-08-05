# Fail Fast — Discount Batch

**Files:** `src/failfast/discountbatch/`

| Package | File | Role |
|---|---|---|
| `example/` | `Order.java` | Shared entity |
| `example/` | `DiscountBatchLate.java` | BEFORE — no upfront validation |
| `example/` | `DiscountBatch.java` | AFTER — validates once, before touching any order |
| `example/` | `FailFastDemo.java` | `main()` — runs everything |
| `exercise/` | `ShippingRateLate.java` | TODO exercise — given unvalidated code |

## What it shows

Applying a discount to a batch of orders, two ways.

1. **BEFORE (`DiscountBatchLate.applyDiscountBatchLate`)** — no upfront
   check on the discount. Pass in `150` (a nonsensical 150% discount)
   and it silently produces a negative price for every order in the
   batch — no error, no warning, just wrong numbers.
2. **AFTER (`DiscountBatch.applyDiscountBatch`)** — validates the
   discount once, before touching a single order, and throws
   immediately with a message that points straight at the problem.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`ShippingRateLate.applyShippingRateLate` (see
`exercise/ShippingRateLate.java`) has the same problem: a negative
shipping rate isn't checked anywhere and silently produces a negative
shipping charge for every order.

**Task:** write `validateShippingRate(double ratePerOrder)` (throws
`IllegalArgumentException` if negative) and `applyShippingRate(orders,
ratePerOrder)` that validates first, then loops. Update
`FailFastDemo.main` to call it, then delete `ShippingRateLate`.

<details>
<summary>Solution</summary>

```java
static void validateShippingRate(double ratePerOrder) {
    if (ratePerOrder < 0) {
        throw new IllegalArgumentException("ratePerOrder must not be negative, got: " + ratePerOrder);
    }
}

static void applyShippingRate(List<Order> orders, double ratePerOrder) {
    validateShippingRate(ratePerOrder);
    for (Order order : orders) {
        double withShipping = order.price() + ratePerOrder;
        System.out.println(order.id() + ": shipping added -> $" + withShipping);
    }
}

// in main():
try {
    applyShippingRate(orders, -5);
} catch (IllegalArgumentException e) {
    System.out.println("Rejected immediately: " + e.getMessage());
}
```

</details>
