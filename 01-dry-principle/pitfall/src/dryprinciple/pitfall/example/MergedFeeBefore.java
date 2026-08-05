package dryprinciple.pitfall.example;

/**
 * BEFORE: two fees that only coincidentally look similar (both are "a
 * small dollar amount computed from the order") get forced into one
 * "DRY" method controlled by a flag. It looks fine at first (step 1 has
 * two clean, independent methods; step 2 merges them and still works)
 * — until requirements diverge (step 3): a delivery-only promo gets
 * added to the shared method and leaks into the unrelated fee.
 */
public class MergedFeeBefore {

    // Step 1: two fees, independent and correct.
    public static double deliveryFeeV1(double distanceKm) {
        if (distanceKm <= 3) return 2.0;
        if (distanceKm <= 8) return 4.0;
        return 6.0;
    }

    public static double orderServiceFeeV1(double subtotal) {
        double fee = subtotal * 0.05;
        return Math.max(1.0, Math.min(fee, 10.0));
    }

    // Step 2: the "DRY" merge — one method, a flag picks the branch.
    public static double calculateFee(double subtotal, double distanceKm, boolean isDelivery) {
        if (isDelivery) {
            if (distanceKm <= 3) return 2.0;
            if (distanceKm <= 8) return 4.0;
            return 6.0;
        } else {
            double fee = subtotal * 0.05;
            return Math.max(1.0, Math.min(fee, 10.0));
        }
    }

    // Step 3: a delivery-only promo gets added to the SHARED method,
    // and because both fees share it, silently waives the service fee too.
    public static double calculateFeeV2(double subtotal, double distanceKm, boolean isDelivery) {
        // BUG: meant to be delivery-only, but affects both fees because they share one method.
        if (subtotal > 50) return 0.0;

        if (isDelivery) {
            if (distanceKm <= 3) return 2.0;
            if (distanceKm <= 8) return 4.0;
            return 6.0;
        } else {
            double fee = subtotal * 0.05;
            return Math.max(1.0, Math.min(fee, 10.0));
        }
    }
}
