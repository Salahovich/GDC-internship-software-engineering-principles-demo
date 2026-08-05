package telldontask.inventoryreservation.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * GiftCardAsk below has the exact same problem InventoryItemAsk had:
 * redeemGiftCardAsk() asks for the balance, checks it, then sets a new
 * one — the "don't go negative" rule lives outside the card, not
 * inside it.
 *
 * Task:
 *   a) Add a redeem(double amount) method to this class that checks the
 *      balance and subtracts internally, throwing
 *      IllegalStateException if the balance is too low.
 *   b) Update TellDontAskDemo.main() to call card.redeem(amount)
 *      directly instead of redeemGiftCardAsk(card, amount).
 *   c) Delete redeemGiftCardAsk() once nothing calls it.
 */
public class GiftCardAsk {
    private double balance;

    public GiftCardAsk(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void redeemGiftCardAsk(GiftCardAsk card, double amount) {
        if (card.getBalance() >= amount) {
            card.setBalance(card.getBalance() - amount);
        } else {
            System.out.println("Rejected: gift card balance too low for $" + amount);
        }
    }
}
