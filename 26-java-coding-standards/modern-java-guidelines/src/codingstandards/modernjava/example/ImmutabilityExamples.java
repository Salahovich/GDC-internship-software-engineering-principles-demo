package codingstandards.modernjava.example;

/** Immutability & records: objects that can't change can't be changed behind your back. */
public class ImmutabilityExamples {

    // BEFORE: mutable — anything holding a reference can change it out from under you
    public static class MoneyMutable {
        private double amount;

        public MoneyMutable(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    // AFTER: a record — constructor, accessors, equals, hashCode and toString in one line,
    // and there's no setter to accidentally call.
    public record Money(double amount, String currency) {}

    /** Simulates another part of the codebase "helpfully" adjusting what it thinks is a local copy. */
    public static void applyRoundingBug(MoneyMutable money) {
        money.setAmount(money.getAmount() - 0.01);
    }
}
