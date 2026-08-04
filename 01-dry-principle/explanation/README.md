# DRY — Explanation

**Slide:** DRY Explanation

**File:** `src/dryprinciple/explanation/DryExplanationDemo.java`

## What it shows

A pricing calculator for three product categories.

1. **BEFORE** — `bookTotal`, `electronicsTotal`, `clothingTotal` each
   repeat the same two rules (tax = subtotal × rate, 10% discount over
   $100) with only the tax rate differing. Change the discount rule and
   you must edit it in three places.
2. **AFTER** — `taxRateFor` + `calculateTotal` express each rule exactly
   once. Same output, one source of truth per rule.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`printInStoreReceipt` and `printOnlineReceipt` (bottom of the file, marked
`TODO`) both repeat the same currency-formatting line and the same
loyalty-points formula.

**Task:** write one shared method, e.g.

```java
static void printReceipt(String channel, Product p, int qty)
```

that computes the total, formats it, computes the points, and prints the
line — passing `"In-Store"` or `"Online"` as the channel label. Replace
the two duplicated methods (and their calls in `main`) with calls to it.

<details>
<summary>Solution</summary>

```java
static void printReceipt(String channel, Product p, int qty) {
    double total = calculateTotal(p, qty);
    String formatted = String.format("$%.2f", total);
    int points = (int) (total / 10);
    System.out.printf("[%-8s] %s x%d = %s (%d pts)%n", channel, p.name(), qty, formatted, points);
}

// in main():
printReceipt("In-Store", book, 2);
printReceipt("Online", book, 2);
```

</details>
