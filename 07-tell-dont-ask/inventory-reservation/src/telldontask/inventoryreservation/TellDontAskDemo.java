package telldontask.inventoryreservation;

/**
 * TELL, DON'T ASK
 * ----------------------------------------------------------------------
 * Instead of asking an object for its internal state, making a decision
 * about it from the outside, and then reaching back in to change it —
 * tell the object what you want done and let IT enforce its own rules.
 * When the decision logic lives outside the object, every call site has
 * to remember to repeat the same check, and it's easy for one of them to
 * forget.
 *
 * This demo reserves stock for a checkout two ways: BEFORE, external
 * code asks for the stock count, checks it, then sets a new value —
 * duplicated across two call sites, one of which forgets the check
 * entirely; AFTER, the item is told to reserve itself and enforces the
 * rule internally, every time, in exactly one place.
 */
public class TellDontAskDemo {

    // ======================================================================
    // 1) BEFORE — violates Tell, Don't Ask.
    //    InventoryItemAsk exposes its stock as a plain getter/setter, so
    //    every caller has to ask for the value, decide, then push a new
    //    value back in. The "don't go negative" rule lives OUTSIDE the
    //    object — in every caller, separately.
    // ======================================================================

    static class InventoryItemAsk {
        private int stock;

        InventoryItemAsk(int stock) {
            this.stock = stock;
        }

        int getStock() {
            return stock;
        }

        void setStock(int stock) {
            this.stock = stock;
        }
    }

    // Regular checkout: asks, checks, then sets. Looks careful enough.
    static void checkoutReserve(InventoryItemAsk item, int qty) {
        if (item.getStock() >= qty) {
            item.setStock(item.getStock() - qty);
        } else {
            System.out.println("Rejected: not enough stock for qty " + qty);
        }
    }

    // Express checkout, added later by someone in a hurry: asks and sets,
    // but forgets to check first. Nothing stopped this — the rule was
    // never the object's own responsibility to enforce.
    static void expressCheckoutReserve(InventoryItemAsk item, int qty) {
        item.setStock(item.getStock() - qty); // BUG: no check — stock can go negative
    }

    // ======================================================================
    // 2) AFTER — Tell, Don't Ask applied.
    //    InventoryItem hides its stock behind reserve(qty). The rule
    //    lives in exactly ONE place, inside the object, and every caller
    //    — no matter how it's written — gets it enforced automatically.
    // ======================================================================

    static class InventoryItem {
        private int stock;

        InventoryItem(int stock) {
            this.stock = stock;
        }

        int getStock() {
            return stock;
        }

        void reserve(int qty) {
            if (qty > stock) {
                throw new IllegalStateException("not enough stock for qty " + qty);
            }
            stock -= qty;
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    GiftCardAsk below has the exact same problem: redeemGiftCardAsk()
    //    asks for the balance, checks it, then sets a new one — the
    //    "don't go negative" rule lives outside the card, not inside it.
    //
    //    Your task:
    //      a) Add a redeem(double amount) method to GiftCardAsk that
    //         checks the balance and subtracts internally, throwing
    //         IllegalStateException if the balance is too low.
    //      b) Update main() to call card.redeem(amount) directly instead
    //         of redeemGiftCardAsk(card, amount).
    //      c) Delete redeemGiftCardAsk() once nothing calls it.
    // ======================================================================

    static class GiftCardAsk {
        private double balance;

        GiftCardAsk(double balance) {
            this.balance = balance;
        }

        double getBalance() {
            return balance;
        }

        void setBalance(double balance) {
            this.balance = balance;
        }
    }

    static void redeemGiftCardAsk(GiftCardAsk card, double amount) {
        if (card.getBalance() >= amount) {
            card.setBalance(card.getBalance() - amount);
        } else {
            System.out.println("Rejected: gift card balance too low for $" + amount);
        }
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: the 'don't go negative' rule lives outside the object ==");
        InventoryItemAsk askItem = new InventoryItemAsk(5);
        checkoutReserve(askItem, 3);
        System.out.println("Stock after normal checkout (qty 3): " + askItem.getStock());
        expressCheckoutReserve(askItem, 10); // forgets the check
        System.out.println("Stock after express checkout (qty 10): " + askItem.getStock() + "  <- negative stock!");

        System.out.println();
        System.out.println("== AFTER: the item enforces its own rule, every time ==");
        InventoryItem item = new InventoryItem(5);
        item.reserve(3);
        System.out.println("Stock after reserve(3): " + item.getStock());
        try {
            item.reserve(10);
        } catch (IllegalStateException e) {
            System.out.println("Rejected by the item itself: " + e.getMessage());
        }
        System.out.println("Stock unchanged: " + item.getStock());

        System.out.println();
        System.out.println("== TODO exercise: encapsulate GiftCardAsk's redeem rule ==");
        GiftCardAsk card = new GiftCardAsk(20.0);
        redeemGiftCardAsk(card, 50.0);
        System.out.println("Gift card balance: $" + card.getBalance());
    }
}
