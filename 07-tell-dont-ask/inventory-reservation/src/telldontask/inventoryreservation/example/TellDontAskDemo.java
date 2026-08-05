package telldontask.inventoryreservation.example;

import telldontask.inventoryreservation.exercise.GiftCardAsk;

/**
 * TELL, DON'T ASK
 * ----------------------------------------------------------------------
 * Instead of asking an object for its internal state, making a decision
 * about it from the outside, and then reaching back in to change it —
 * tell the object what you want done and let IT enforce its own rules.
 * When the decision logic lives outside the object, every call site has
 * to remember to repeat the same check, and it's easy for one of them to
 * forget.
 */
public class TellDontAskDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: the 'don't go negative' rule lives outside the object ==");
        InventoryItemAsk askItem = new InventoryItemAsk(5);
        InventoryItemAsk.checkoutReserve(askItem, 3);
        System.out.println("Stock after normal checkout (qty 3): " + askItem.getStock());
        InventoryItemAsk.expressCheckoutReserve(askItem, 10); // forgets the check
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
        System.out.println("== TODO exercise: see exercise/GiftCardAsk.java ==");
        GiftCardAsk card = new GiftCardAsk(20.0);
        GiftCardAsk.redeemGiftCardAsk(card, 50.0);
        System.out.println("Gift card balance: $" + card.getBalance());
    }
}
