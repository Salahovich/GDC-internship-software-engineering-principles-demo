# Builder — Order Builder

**Files:** `src/designpatterns/builder/orderbuilder/`

| Package | File | Role |
|---|---|---|
| `example/` | `TelescopingOrder.java` | BEFORE — a constructor overload per combination of options |
| `example/` | `Order.java` | AFTER — construction via nested `Builder` |
| `example/` | `BuilderDemo.java` | `main()` — runs everything |
| `exercise/` | `ShippingLabelBuilderTodo.java` | TODO exercise instructions |

## What it shows

Building an order with some required fields and some optional ones
(gift message, gift wrap), two ways.

1. **BEFORE (`TelescopingOrder`)** — a constructor overload for every
   combination of optional fields. Callers have to know which overload
   takes which flag, in which order — and a fifth optional field would
   mean yet more overloads.
2. **AFTER (`Order` + nested `Builder`)** — required fields go into the
   builder's constructor, optional ones get named, chainable methods
   (`.giftMessage(...)`, `.giftWrapped(...)`), and `.build()` returns the
   finished, immutable `Order`. The call site reads top to bottom instead
   of matching positions to a constructor signature.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Shipping labels have the same telescoping problem (see
`exercise/ShippingLabelBuilderTodo.java` for the given `TelescopingShippingLabel`
constructors).

**Task:** write `ShippingLabel` with a nested `Builder` — required fields
(recipient, address) in the builder's constructor, optional ones
(fragile, notes) via chainable methods — the same shape as
`Order.Builder`.

<details>
<summary>Solution</summary>

```java
public class ShippingLabel {
    private final String recipient;
    private final String address;
    private final boolean fragile;
    private final String notes;

    private ShippingLabel(Builder builder) {
        this.recipient = builder.recipient;
        this.address = builder.address;
        this.fragile = builder.fragile;
        this.notes = builder.notes;
    }

    public static class Builder {
        private final String recipient;
        private final String address;
        private boolean fragile;
        private String notes;

        public Builder(String recipient, String address) {
            this.recipient = recipient;
            this.address = address;
        }

        public Builder fragile(boolean fragile) {
            this.fragile = fragile;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public ShippingLabel build() {
            return new ShippingLabel(this);
        }
    }
}
```

</details>
