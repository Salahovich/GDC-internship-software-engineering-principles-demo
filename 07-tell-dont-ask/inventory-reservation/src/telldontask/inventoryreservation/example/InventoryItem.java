package telldontask.inventoryreservation.example;

/**
 * AFTER: Tell, Don't Ask applied. InventoryItem hides its stock behind
 * reserve(qty). The rule lives in exactly ONE place, inside the object,
 * and every caller — no matter how it's written — gets it enforced
 * automatically.
 */
public class InventoryItem {
    private int stock;

    public InventoryItem(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void reserve(int qty) {
        if (qty > stock) {
            throw new IllegalStateException("not enough stock for qty " + qty);
        }
        stock -= qty;
    }
}
