package dryprinciple.explanation.example;

/**
 * BEFORE: violates DRY. Every method repeats the SAME two rules —
 * "tax = subtotal * rate" (only the rate differs per category) and
 * "orders over $100 get 10% off". If the discount threshold ever
 * changes, someone has to remember to update it in all three places.
 */
public class DuplicatedPricing {

    public static double bookTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double tax = subtotal * 0.05; // books: 5% tax
        double total = subtotal + tax;
        if (subtotal > 100) total *= 0.90; // bulk discount
        return round2(total);
    }

    public static double electronicsTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double tax = subtotal * 0.15; // electronics: 15% tax
        double total = subtotal + tax;
        if (subtotal > 100) total *= 0.90; // same discount, copy-pasted
        return round2(total);
    }

    public static double clothingTotal(Product p, int qty) {
        double subtotal = p.price() * qty;
        double tax = subtotal * 0.08; // clothing: 8% tax
        double total = subtotal + tax;
        if (subtotal > 100) total *= 0.90; // same discount, copy-pasted again
        return round2(total);
    }

    private static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
