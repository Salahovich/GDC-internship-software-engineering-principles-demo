# SOLID (O) — Order Discounts

**Files:** `src/solidocp/orderdiscounts/`

| Package | File | Role |
|---|---|---|
| `example/` | `OrderItem.java`, `Order.java` | Shared entities |
| `before/` | `DiscountCalculatorOcpViolation.java` | BEFORE — `if/else` on customer type |
| `example/` | `DiscountPolicy.java` | AFTER — interface + `RegularDiscount`/`VipDiscount`/`StudentDiscount` |
| `example/` | `OrderTotalCalculator.java` | AFTER — never changes when a new type is added |
| `example/` | `OcpDemo.java` | `main()` — runs everything |
| `exercise/` | `ShippingCostCalculatorOcpViolation.java` | TODO exercise — given `if/else` region chain |

## What it shows

Calculating a discounted order total, two ways.

1. **BEFORE (`DiscountCalculatorOcpViolation`)** — branches on the
   customer type with if/else. Adding a new customer type ("EMPLOYEE",
   say) means opening up and editing this class.
2. **AFTER (`DiscountPolicy` + `OrderTotalCalculator`)** — each customer
   type is its own class implementing `DiscountPolicy`. Adding a new
   type means adding a new class; `OrderTotalCalculator` never changes
   again.

This demo introduces the `DiscountPolicy` abstraction that
[`14-solid-lsp/`](../../14-solid-lsp/discount-policies/README.md) builds
on next.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`ShippingCostCalculatorOcpViolation` (see
`exercise/ShippingCostCalculatorOcpViolation.java`) has the same
problem: adding a shipping region means editing its if/else chain.

**Task:** create a `ShippingPolicy` interface with `getFlatRate()` (in
its own file), implement `LocalShipping` ($3), `NationalShipping` ($7),
and `InternationalShipping` ($20), and a `ShippingCostCalculator` that
takes a `ShippingPolicy`. Update `OcpDemo.main` to use it, then delete
`ShippingCostCalculatorOcpViolation`.

<details>
<summary>Solution</summary>

```java
public interface ShippingPolicy {
    double getFlatRate();
}

public class LocalShipping implements ShippingPolicy {
    public double getFlatRate() { return 3.0; }
}

public class NationalShipping implements ShippingPolicy {
    public double getFlatRate() { return 7.0; }
}

public class InternationalShipping implements ShippingPolicy {
    public double getFlatRate() { return 20.0; }
}

public class ShippingCostCalculator {
    double calculateShipping(ShippingPolicy policy) {
        return policy.getFlatRate();
    }
}

// in main():
ShippingCostCalculator shippingCalculator = new ShippingCostCalculator();
System.out.println("Local shipping: $" + shippingCalculator.calculateShipping(new LocalShipping()));
System.out.println("International shipping: $" + shippingCalculator.calculateShipping(new InternationalShipping()));
```

</details>
