# Prototype — Order Template

**Files:** `src/designpatterns/prototype/ordertemplate/`

Follows Refactoring Guru's Java Prototype example
(https://refactoring.guru/design-patterns/prototype/java/example) — a
copy-constructor-based abstract base, not `Cloneable`/`clone()`, cloned
polymorphically across a heterogeneous collection — applied to orders
instead of shapes.

| Package | File | Role |
|---|---|---|
| `before/` | `OrderManualCopy.java` | BEFORE — hand-written copy, shares the items list |
| `example/` | `Order.java` | Abstract base — copy constructor + abstract `copy()` |
| `example/` | `StandardOrder.java` | Concrete prototype #1 — no extra fields |
| `example/` | `GiftOrder.java` | Concrete prototype #2 — adds its own `giftMessage` field |
| `example/` | `PrototypeDemo.java` | `main()` — runs everything |
| `exercise/` | `QuoteCopyTodo.java` | TODO exercise instructions |

## What it shows

Duplicating an order for a "reorder" feature, two ways.

1. **BEFORE (`OrderManualCopy.copyForgettingItems()`)** — passes the same
   `items` List reference into the new object instead of copying it.
   Editing the "copy"'s items silently mutates the original too.
2. **AFTER (`Order` / `StandardOrder` / `GiftOrder`)** — `Order` is
   abstract, with a protected **copy constructor** (`Order(Order
   target)`) that every subclass chains to via `super(target)`, plus an
   abstract `copy()` each subclass implements to return its own type.
   This is the actual point, and where the old single-class version fell
   short: `PrototypeDemo.cloneAll()` copies a `List<Order>` holding BOTH
   a `StandardOrder` and a `GiftOrder` — two unrelated concrete
   classes — and never once checks which one it's looking at. Add a
   third order type tomorrow and `cloneAll()` still doesn't change.

Run it:

```bash
./run.sh
```

Sample output shows, for each template: that the clone is a different
object (`==` is false) but has equal field values (`.equals()` is true) —
then mutates one clone's items and confirms the original template's list
is untouched.

## Exercise (~5 minutes)

Sales wants to copy a saved "template" `Quote` for each new customer
without the template changing (see `exercise/QuoteCopyTodo.java` for the
given `Quote` shape).

**Task:** give `Quote` a copy constructor and a `copy()` method — same
shape as `StandardOrder` — that builds a new list from the original
items instead of reusing the reference.

<details>
<summary>Solution</summary>

```java
public class Quote {
    private String customerEmail;
    private List<String> items;
    private double discountRate;

    public Quote(String customerEmail, List<String> items, double discountRate) {
        this.customerEmail = customerEmail;
        this.items = items;
        this.discountRate = discountRate;
    }

    protected Quote(Quote target) {
        this.customerEmail = target.customerEmail;
        this.items = new ArrayList<>(target.items);
        this.discountRate = target.discountRate;
    }

    public Quote copy() {
        return new Quote(this);
    }
}
```

</details>
