# YAGNI — Extra Fields the Business Doesn't Need

**File:** `src/yagniprinciple/customerfields/YagniDemo.java`

## What it shows

A `Customer` used to send an order confirmation email, two ways.

1. **BEFORE (`CustomerOverEngineered`)** — carries `loyaltyTier`,
   `referralCode`, `preferredLanguage`, `birthDate`, and an
   `extraMetadata` map, all added "in case we need them later." No
   current feature reads any of them — `buildConfirmationEmail` only
   ever touches `name` and `email` — yet every caller has to supply all
   7 constructor arguments (usually `null` for the 5 that don't apply).
2. **AFTER (`Customer`, a record)** — exactly the two fields today's
   feature needs. If a loyalty program becomes a real requirement, add
   `loyaltyTier` *then*, designed against the actual spec, not a guess.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`OrderOverEngineered` (bottom of the file, marked `TODO`) has the same
problem: `estimatedCarbonFootprint`, `vipPriorityFlag`, and
`giftWrapNoteHistory` were added speculatively and are never read by
`calculateTotal`, the only method that exists today.

**Task:** create a trimmed-down `Order` (a record works well) with only
`itemPrices` and `discountPercent`, write a `calculateTotal` that takes
it, update `main` to use it, then delete `OrderOverEngineered`.

<details>
<summary>Solution</summary>

```java
record Order(List<Double> itemPrices, double discountPercent) {}

static double calculateTotal(Order order) {
    double subtotal = order.itemPrices().stream().mapToDouble(Double::doubleValue).sum();
    return subtotal * (1 - order.discountPercent() / 100);
}

// in main():
Order order = new Order(List.of(19.99, 34.50), 10.0);
System.out.println("Total: $" + calculateTotal(order));
```

</details>
