# DRY — Pitfall (the wrong abstraction)

**Files:** `src/dryprinciple/pitfall/`

| Package | File | Role |
|---|---|---|
| `example/` | `MergedFeeBefore.java` | BEFORE — two fees forced into one method |
| `example/` | `SplitFeeAfter.java` | AFTER — split back into independent methods |
| `example/` | `DryPitfallDemo.java` | `main()` — runs everything |
| `exercise/` | `ExtraChargeTangled.java` | TODO exercise — given tangled code |

## What it shows

A checkout has two small charges: a **delivery fee** (based on distance)
and an **order service fee** (5% of the subtotal, floor $1, cap $10).
They only *coincidentally* look similar — both are "compute a small
dollar amount from the order." `MergedFeeBefore` "DRY's" them into one
`calculateFee(subtotal, distanceKm, isDelivery)` method. It looks fine
at first.

Then a requirement diverges: marketing wants "free delivery on orders
over $50." Because both fees share one method, the check gets added at
the top of it — and it silently waives the *order service fee* too, not
just delivery (see Step 3 output: an $80 order's service fee wrongly
becomes $0.00 instead of $4.00).

**Lesson:** duplication is cheaper than the wrong abstraction. Two
charges that only look alike today, but follow different rules, should
stay separate — a little repeated code is fine and safer than false
coupling. `SplitFeeAfter` shows the fix: split back into `deliveryFee`
and `orderServiceFee`, each independent, so the delivery promo can never
leak into the service fee again.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`ExtraChargeTangled.calculateExtraCharge(type, ...)` (see
`exercise/ExtraChargeTangled.java`) controls two unrelated charges — a
packaging fee and a rush-hour surcharge — with a `type` flag, the same
pattern that caused the bug above.

**Task:** split it into two independent methods:

```java
static double packagingFee(double subtotal)
static double rushFee(boolean isPeakTime)
```

Update the calls in `DryPitfallDemo.main` to use them instead of
`calculateExtraCharge(...)`, then delete `ExtraChargeTangled`.

<details>
<summary>Solution</summary>

```java
static double packagingFee(double subtotal) {
    return subtotal < 20 ? 1.0 : 0.0;
}

static double rushFee(boolean isPeakTime) {
    return isPeakTime ? 3.0 : 0.0;
}

// in main():
System.out.println("Packaging fee ($15 order): $" + packagingFee(15));
System.out.println("Rush fee (peak time):      $" + rushFee(true));
```

</details>
