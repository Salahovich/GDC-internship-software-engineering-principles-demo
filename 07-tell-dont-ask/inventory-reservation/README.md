# Tell, Don't Ask — Inventory Reservation

**File:** `src/telldontask/inventoryreservation/TellDontAskDemo.java`

## What it shows

Reserving stock for a checkout, two ways.

1. **BEFORE (`InventoryItemAsk`)** — a plain getter/setter. `checkoutReserve`
   asks for the stock, checks it, then sets a new value. A second call
   site, `expressCheckoutReserve`, added later, asks and sets too — but
   forgets the check, and stock silently goes negative. Nothing stopped
   this, because the rule lived outside the object, not inside it.
2. **AFTER (`InventoryItem`)** — stock is hidden behind `reserve(qty)`.
   The rule ("never go negative") lives in exactly one place, inside the
   object, and applies no matter how the method is called.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`GiftCardAsk` (bottom of the file, marked `TODO`) has the same problem:
`redeemGiftCardAsk` asks for the balance, checks it, then sets a new
one — the "don't go negative" rule lives outside the card.

**Task:** add a `redeem(double amount)` method to `GiftCardAsk` that
checks the balance and subtracts internally, throwing
`IllegalStateException` if the balance is too low. Update `main` to call
`card.redeem(amount)` directly, then delete `redeemGiftCardAsk`.

<details>
<summary>Solution</summary>

```java
static class GiftCardAsk {
    private double balance;

    GiftCardAsk(double balance) {
        this.balance = balance;
    }

    double getBalance() {
        return balance;
    }

    void redeem(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("gift card balance too low for $" + amount);
        }
        balance -= amount;
    }
}

// in main():
GiftCardAsk card = new GiftCardAsk(20.0);
try {
    card.redeem(50.0);
} catch (IllegalStateException e) {
    System.out.println("Rejected by the card itself: " + e.getMessage());
}
```

</details>
