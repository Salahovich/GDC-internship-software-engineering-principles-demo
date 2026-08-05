package dryprinciple.pitfall.example;

/**
 * AFTER: split back into two small, independent methods. Each fee now
 * owns its own rules and can change without touching the other. The
 * "free over $50" promo only ever existed for delivery.
 */
public class SplitFeeAfter {

    public static double deliveryFee(double distanceKm, double subtotal) {
        if (subtotal > 50) return 0.0; // free delivery promo — delivery only
        if (distanceKm <= 3) return 2.0;
        if (distanceKm <= 8) return 4.0;
        return 6.0;
    }

    public static double orderServiceFee(double subtotal) {
        double fee = subtotal * 0.05;
        return Math.max(1.0, Math.min(fee, 10.0)); // always charged, no promo
    }
}
