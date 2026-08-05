package dryprinciple.explanation.example;

/**
 * AFTER: DRY refactor. The tax rule lives in exactly one place
 * (taxRateFor), and the pricing rule lives in exactly one place
 * (calculateTotal). Adding a new category is a one-line change;
 * changing the discount rule is a one-line change.
 */
public class UnifiedPricing {

    public static double taxRateFor(String category) {
        return switch (category) {
            case "BOOK" -> 0.05;
            case "ELECTRONICS" -> 0.15;
            case "CLOTHING" -> 0.08;
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        };
    }

    public static double calculateTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double total = subtotal + subtotal * taxRateFor(p.category());
        if (subtotal > 100) total *= 0.90;
        return round2(total);
    }

    public static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
