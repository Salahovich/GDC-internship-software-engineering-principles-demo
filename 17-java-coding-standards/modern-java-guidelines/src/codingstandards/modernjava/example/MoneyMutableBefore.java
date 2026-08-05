package codingstandards.modernjava.example;

/**
 * BEFORE: mutable — anything holding a reference can change it out
 * from under you.
 */
public class MoneyMutableBefore {
    private double amount;

    public MoneyMutableBefore(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /** Simulates another part of the codebase "helpfully" adjusting what it thinks is a local copy. */
    public static void applyRoundingBug(MoneyMutableBefore money) {
        money.setAmount(money.getAmount() - 0.01);
    }
}
