# Cohesion & Coupling — Store Operations

**File:** `src/cohesionandcoupling/storeoperations/CohesionAndCouplingDemo.java`

## What it shows

Running a store's inventory, payroll, and promotions, two ways.

1. **BEFORE (`StoreManagerLowCohesion`)** — one class does three
   unrelated jobs: restocking shelves, calculating paychecks, and
   emailing coupons. Its constructor gives it away: even a caller that
   only ever wants to check stock is forced to supply a pay rate table
   and an email sender address too. **Low cohesion** (unrelated
   responsibilities crammed together) directly caused **unnecessary
   coupling** (every caller now depends on payroll and email, whether it
   needs them or not).
2. **AFTER (`InventoryManager`, `PayrollCalculator`,
   `PromotionNotifier`)** — three small, **highly cohesive** classes,
   each with one reason to change. `printStockReport()` depends on
   `InventoryManager` alone — it was never coupled to payroll or email in
   the first place.

That's the relationship the demo is built to show: raising cohesion
(splitting apart what shouldn't have been together) is usually exactly
what lowers coupling elsewhere in the system.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`ReceiptPrinterLowCohesion` (bottom of the file, marked `TODO`) bundles
two unrelated jobs: formatting a receipt line and tracking a running
loyalty-points balance. A caller that only wants to format text is still
forced to depend on the loyalty points store.

**Task:** split it into `ReceiptFormatter.formatLine(itemName, price)`
and `LoyaltyPointsTracker` (`addPoints(customerId, points)`,
`getPoints(customerId)`). Update `main` to use the two new classes
directly, then delete `ReceiptPrinterLowCohesion`.

<details>
<summary>Solution</summary>

```java
static class ReceiptFormatter {
    String formatLine(String itemName, double price) {
        return " - " + itemName + ": $" + price;
    }
}

static class LoyaltyPointsTracker {
    private final Map<String, Integer> loyaltyPoints = new HashMap<>();

    void addPoints(String customerId, int points) {
        loyaltyPoints.merge(customerId, points, Integer::sum);
    }

    int getPoints(String customerId) {
        return loyaltyPoints.getOrDefault(customerId, 0);
    }
}

// in main():
ReceiptFormatter formatter = new ReceiptFormatter();
System.out.println(formatter.formatLine("Keyboard", 60.0));

LoyaltyPointsTracker points = new LoyaltyPointsTracker();
points.addPoints("amina", 6);
System.out.println("Amina's points: " + points.getPoints("amina"));
```

</details>
