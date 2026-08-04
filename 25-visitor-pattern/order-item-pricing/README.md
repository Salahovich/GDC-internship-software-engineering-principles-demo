# Visitor — Order Item Pricing

**Files:** `src/designpatterns/visitor/orderitempricing/`

| Package | File | Role |
|---|---|---|
| `example/` | `PhysicalItem.java`, `DigitalItem.java` | The item family being priced |
| `example/` | `PricingCalculatorInstanceOf.java` | BEFORE — instanceof chain over `Object` items |
| `example/` | `OrderItemVisitor.java` | AFTER — the visitor interface |
| `example/` | `PricingVisitor.java` | AFTER — pricing implemented as a visitor |
| `example/` | `VisitorDemo.java` | `main()` — runs everything |
| `exercise/` | `ShippingLabelVisitorTodo.java` | TODO exercise instructions |

## What it shows

Pricing a mixed order (a physical mug, a digital ebook), two ways.

1. **BEFORE (`PricingCalculatorInstanceOf`)** — takes `List<Object>` and
   dispatches with `instanceof PhysicalItem` / `instanceof DigitalItem`.
   Every new item type means editing this class; every new operation
   over the same items (shipping labels, tax reports) means writing a
   fresh instanceof chain that repeats the same type checks.
2. **AFTER (`OrderItemVisitor` + `PricingVisitor`)** — each item type
   gets an `accept(OrderItemVisitor visitor)` method that calls back into
   the matching `visitPhysical` / `visitDigital`. This is double
   dispatch: the item picks the visitor method, the visitor holds the
   type-specific logic. `PricingVisitor` computes the same totals with no
   instanceof, and the compiler forces every visitor to handle both item
   types.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Warehouse ops wants a second operation: shipping labels. Only
`PhysicalItem` actually ships (see `exercise/ShippingLabelVisitorTodo.java`
for the full brief).

**Task:** write `ShippingLabelVisitor implements OrderItemVisitor` (new
file) that prints a label line for physical items and a "no shipping
needed" line for digital ones. You should not need to touch
`PhysicalItem`, `DigitalItem`, or `PricingVisitor` to do this.

<details>
<summary>Solution</summary>

```java
public class ShippingLabelVisitor implements OrderItemVisitor {
    public double visitPhysical(PhysicalItem item) {
        System.out.println("Ship " + item.getName() + " (" + item.getWeightKg() + " kg)");
        return 1;
    }
    public double visitDigital(DigitalItem item) {
        System.out.println(item.getName() + " is a digital download — no shipping label needed");
        return 0;
    }
}
```

</details>
