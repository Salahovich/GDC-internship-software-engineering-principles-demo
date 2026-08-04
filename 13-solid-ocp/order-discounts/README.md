# SOLID (O) — Order Discounts

**File:** `src/solidocp/orderdiscounts/OcpDemo.java`

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

`ShippingCostCalculatorOcpViolation` (bottom of the file, marked `TODO`)
has the same problem: adding a shipping region means editing its
if/else chain.

**Task:** create a `ShippingPolicy` interface with `getFlatRate()`,
implement `LocalShipping` ($3), `NationalShipping` ($7), and
`InternationalShipping` ($20), and a `ShippingCostCalculator` that takes
a `ShippingPolicy`. Update `main` to use it, then delete
`ShippingCostCalculatorOcpViolation`.

<details>
<summary>Solution</summary>

```java
interface ShippingPolicy {
    double getFlatRate();
}

static class LocalShipping implements ShippingPolicy {
    public double getFlatRate() { return 3.0; }
}

static class NationalShipping implements ShippingPolicy {
    public double getFlatRate() { return 7.0; }
}

static class InternationalShipping implements ShippingPolicy {
    public double getFlatRate() { return 20.0; }
}

static class ShippingCostCalculator {
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
