# Prototype — Order Template

**Files:** `src/designpatterns/prototype/ordertemplate/`

| Package | File | Role |
|---|---|---|
| `example/` | `OrderManualCopy.java` | BEFORE — hand-written copy, shares the items list |
| `example/` | `Order.java` | AFTER — has a correct `copy()` method |
| `example/` | `PrototypeDemo.java` | `main()` — runs everything |
| `exercise/` | `QuoteCopyTodo.java` | TODO exercise instructions |

## What it shows

Duplicating an order for a "reorder" feature, two ways.

1. **BEFORE (`OrderManualCopy.copyForgettingItems()`)** — passes the same
   `items` List reference into the new object instead of copying it.
   Editing the "copy"'s items silently mutates the original too.
2. **AFTER (`Order.copy()`)** — builds a new `ArrayList` from the
   original items, so the copy is fully independent. The copying logic
   lives inside the class itself, next to the fields it has to know
   about.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Sales wants to copy a saved "template" `Quote` for each new customer
without the template changing (see `exercise/QuoteCopyTodo.java` for the
given `Quote` shape).

**Task:** add a `copy()` method to `Quote` — same shape as `Order.copy()`
— that builds a new list from the original items instead of reusing the
reference.

<details>
<summary>Solution</summary>

```java
public Quote copy() {
    return new Quote(customerEmail, new ArrayList<>(items), discountRate);
}
```

</details>
