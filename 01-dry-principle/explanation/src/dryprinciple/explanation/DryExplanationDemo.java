package dryprinciple.explanation;

/**
 * DRY PRINCIPLE — PART 1: EXPLANATION
 * ------------------------------------
 * "Don't Repeat Yourself": every piece of knowledge (a business rule, a
 * calculation, a format) should have a single, unambiguous representation
 * in the codebase. When the SAME rule is copy-pasted in multiple places,
 * a change to that rule means hunting down every copy — and it's easy to
 * miss one, leaving the system inconsistent.
 *
 * This demo shows the same pricing rule duplicated across three methods
 * (BEFORE), then collapsed into one shared method (AFTER).
 */
public class DryExplanationDemo {

    // ---- Domain model ----------------------------------------------------
    record Product(String name, String category, double price) {}

    // ======================================================================
    // 1) BEFORE — violates DRY.
    //    Every method repeats the SAME two rules:
    //      - "tax = subtotal * rate" (only the rate differs per category)
    //      - "orders over $100 get 10% off"
    //    If the discount threshold ever changes, someone has to remember
    //    to update it in all three places.
    // ======================================================================

    static double bookTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double tax = subtotal * 0.05;               // books: 5% tax
        double total = subtotal + tax;
        if (subtotal > 100) total *= 0.90;           // bulk discount
        return round2(total);
    }

    static double electronicsTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double tax = subtotal * 0.15;                // electronics: 15% tax
        double total = subtotal + tax;
        if (subtotal > 100) total *= 0.90;           // same discount, copy-pasted
        return round2(total);
    }

    static double clothingTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double tax = subtotal * 0.08;                // clothing: 8% tax
        double total = subtotal + tax;
        if (subtotal > 100) total *= 0.90;           // same discount, copy-pasted again
        return round2(total);
    }

    // ======================================================================
    // 2) AFTER — DRY refactor.
    //    The tax rule lives in exactly one place (taxRateFor), and the
    //    pricing rule lives in exactly one place (calculateTotal). Adding a
    //    new category is a one-line change; changing the discount rule is
    //    a one-line change.
    // ======================================================================

    static double taxRateFor(String category) {
        return switch (category) {
            case "BOOK" -> 0.05;
            case "ELECTRONICS" -> 0.15;
            case "CLOTHING" -> 0.08;
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        };
    }

    static double calculateTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double total = subtotal + subtotal * taxRateFor(p.category());
        if (subtotal > 100) total *= 0.90;
        return round2(total);
    }

    static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    These two receipt printers were added later and both repeat the
    //    SAME currency-formatting rule and the SAME loyalty-points rule.
    //    Your task:
    //      a) Write one shared method, e.g.
    //           static void printReceipt(String channel, Product p, int qty)
    //         that computes the total, formats it, computes points, and
    //         prints the line — taking "In-Store" or "Online" as the label.
    //      b) Replace the two methods below (and their calls in main) with
    //         calls to your new shared method.
    //    Goal: end up with ONE place that knows "how a receipt line looks",
    //    just like calculateTotal() is the ONE place that knows the pricing
    //    rule.
    // ======================================================================

    static void printInStoreReceipt(Product p, int qty) {
        double total = calculateTotal(p, qty);
        String formatted = String.format("$%.2f", total);   // duplicated
        int points = (int) (total / 10);                    // duplicated
        System.out.println("[In-Store] " + p.name() + " x" + qty + " = " + formatted + " (" + points + " pts)");
    }

    static void printOnlineReceipt(Product p, int qty) {
        double total = calculateTotal(p, qty);
        String formatted = String.format("$%.2f", total);   // duplicated
        int points = (int) (total / 10);                    // duplicated
        System.out.println("[Online]   " + p.name() + " x" + qty + " = " + formatted + " (" + points + " pts)");
    }

    public static void main(String[] args) {
        Product book = new Product("Clean Code", "BOOK", 40);
        Product laptop = new Product("ThinkPad", "ELECTRONICS", 900);
        Product shirt = new Product("T-Shirt", "CLOTHING", 25);

        System.out.println("== BEFORE: duplicated logic across 3 near-identical methods ==");
        System.out.println("Book total:        $" + bookTotal(book, 2));
        System.out.println("Electronics total: $" + electronicsTotal(laptop, 1));
        System.out.println("Clothing total:    $" + clothingTotal(shirt, 5));

        System.out.println();
        System.out.println("== AFTER: one method, single source of truth ==");
        System.out.println("Book total:        $" + calculateTotal(book, 2));
        System.out.println("Electronics total: $" + calculateTotal(laptop, 1));
        System.out.println("Clothing total:    $" + calculateTotal(shirt, 5));
        System.out.println("(Same results — the tax rule now lives in exactly one place: taxRateFor())");

        System.out.println();
        System.out.println("== TODO exercise: remove the duplication below ==");
        printInStoreReceipt(book, 2);
        printOnlineReceipt(book, 2);
    }
}
