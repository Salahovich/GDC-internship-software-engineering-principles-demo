# SOLID (L) — Discount Policies

**Files:** `src/solidlsp/discountpolicies/`

| Package | File | Role |
|---|---|---|
| `example/` | `OrderItem.java`, `Order.java`, `DiscountPolicy.java` | Shared entities + interface (from 13-solid-ocp) |
| `example/` | `OrderTotalCalculator.java`, `PrintSavings.java` | Helpers |
| `before/` | `RushFeeAsDiscountPolicyBefore.java` | BEFORE — a surcharge disguised as a `DiscountPolicy` |
| `example/` | `PriceAdjustment.java` | AFTER — interface + `DiscountAdjustment`/`RushFeeSurcharge` |
| `example/` | `LspDemo.java` | `main()` — runs everything |
| `exercise/` | `FreeShippingAsDiscountPolicy.java` | TODO exercise — given `DiscountPolicy` misuse |

## What it shows

Continuing the `DiscountPolicy` abstraction from
[`13-solid-ocp/`](../../13-solid-ocp/order-discounts/README.md), where
every implementation has always promised `0 <= discount <= 100` (a
reduction, never an increase).

1. **BEFORE (`RushFeeAsDiscountPolicyBefore`)** — implements
   `DiscountPolicy` and compiles fine, but returns `-20`, meaning a 20%
   *surcharge* — the total goes up, not down. `PrintSavings.printSavings()`,
   client code that trusts every `DiscountPolicy` reduces the price,
   ends up printing a nonsensical negative "savings" amount. Satisfying
   the method signature wasn't enough — this subtype broke the contract
   every other `DiscountPolicy` had honored.
2. **AFTER (`PriceAdjustment` / `DiscountAdjustment` /
   `RushFeeSurcharge`)** — a surcharge gets its own honest abstraction
   instead of being disguised as a discount. Every `DiscountPolicy` still
   means exactly what it always meant.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`FreeShippingAsDiscountPolicy` (see
`exercise/FreeShippingAsDiscountPolicy.java`) has the same problem: it
implements `DiscountPolicy` and returns `100` ("free"), but it's really
about waiving shipping, not the item price. Plugged into
`PrintSavings.printSavings()`, it wrongly claims the *entire order* was
free.

**Task:** model "shipping is free" as its own concept — reuse
`PriceAdjustment` from the example package, or create a small
`ShippingWaiver` interface — instead of forcing it into `DiscountPolicy`.
Update `LspDemo.main` to apply it as its own concept, then delete
`FreeShippingAsDiscountPolicy`.

<details>
<summary>Solution</summary>

```java
public class FreeShippingWaiver {
    // Applies only to a shipping cost passed in separately — never to
    // the item subtotal, so it can never be mistaken for an item
    // discount the way FreeShippingAsDiscountPolicy was.
    double applyTo(double shippingCost) {
        return 0.0;
    }
}

// in main():
FreeShippingWaiver waiver = new FreeShippingWaiver();
System.out.println("Shipping cost after waiver: $" + waiver.applyTo(7.0));
System.out.println(printSavings(order, new VipDiscount())); // item savings stay honest
```

</details>
