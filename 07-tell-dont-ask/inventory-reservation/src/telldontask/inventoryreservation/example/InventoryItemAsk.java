package telldontask.inventoryreservation.example;

/**
 * BEFORE: violates Tell, Don't Ask. InventoryItemAsk exposes its stock
 * as a plain getter/setter, so every caller has to ask for the value,
 * decide, then push a new value back in. The "don't go negative" rule
 * lives OUTSIDE the object — in every caller, separately.
 */
public class InventoryItemAsk {
    private int stock;

    public InventoryItemAsk(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Regular checkout: asks, checks, then sets. Looks careful enough.
    public static void checkoutReserve(InventoryItemAsk item, int qty) {
        if (item.getStock() >= qty) {
            item.setStock(item.getStock() - qty);
        } else {
            System.out.println("Rejected: not enough stock for qty " + qty);
        }
    }

    // Express checkout, added later by someone in a hurry: asks and sets,
    // but forgets to check first. Nothing stopped this — the rule was
    // never the object's own responsibility to enforce.
    public static void expressCheckoutReserve(InventoryItemAsk item, int qty) {
        item.setStock(item.getStock() - qty); // BUG: no check — stock can go negative
    }
}
