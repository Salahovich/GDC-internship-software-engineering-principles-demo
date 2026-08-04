package dryprinciple.pitfall;

/**
 * DRY PRINCIPLE — PART 2: THE PITFALL (a.k.a. "the wrong abstraction")
 * ----------------------------------------------------------------------
 * DRY is about not repeating the same KNOWLEDGE/RULE. It is easy to
 * over-apply: two pieces of code that merely LOOK similar today, but
 * represent different rules that happen to coincide, are NOT duplication.
 * Forcing them to share one method just because they currently look alike
 * creates a false abstraction — and every future change to either flow
 * risks breaking the other one.
 *
 * This demo "DRY's" a checkout's delivery fee and order service fee —
 * two charges that only coincidentally look similar (both are "a small
 * dollar amount computed from the order") — and shows the regression
 * that shared abstraction causes once the two fees' rules diverge. Then
 * it shows the fix: splitting them back into two small, independent
 * methods.
 */
public class DryPitfallDemo {

    // ======================================================================
    // 1) Two fees start out looking "the same": both are small charges
    //    derived from the order, both computed with a couple of if/else
    //    branches.
    // ======================================================================

    // Delivery fee: based on distance.
    static double deliveryFeeV1(double distanceKm) {
        if (distanceKm <= 3) return 2.0;
        if (distanceKm <= 8) return 4.0;
        return 6.0;
    }

    // Order service fee: 5% of the subtotal, floor $1, cap $10.
    static double orderServiceFeeV1(double subtotal) {
        double fee = subtotal * 0.05;
        return Math.max(1.0, Math.min(fee, 10.0));
    }

    // ======================================================================
    // 2) The "DRY" merge — someone notices both are "compute a small fee
    //    from order data" and folds them into one method controlled by a
    //    flag. This still LOOKS fine...
    // ======================================================================

    static double calculateFee(double subtotal, double distanceKm, boolean isDelivery) {
        if (isDelivery) {
            if (distanceKm <= 3) return 2.0;
            if (distanceKm <= 8) return 4.0;
            return 6.0;
        } else {
            double fee = subtotal * 0.05;
            return Math.max(1.0, Math.min(fee, 10.0));
        }
    }

    // ======================================================================
    // 3) ...until requirements diverge. Marketing wants "free delivery on
    //    orders over $50". A dev adds the check to the SHARED method
    //    instead of splitting it back out — and places it before the
    //    branch, so it applies to BOTH fees instead of just delivery.
    // ======================================================================

    static double calculateFeeV2(double subtotal, double distanceKm, boolean isDelivery) {
        // BUG: this was meant to be a delivery-only promo, but because both
        // fees share one method, it silently waives the order service fee
        // too whenever the subtotal is over $50.
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

    // ======================================================================
    // 4) The fix — split back into two small, independent methods. Each
    //    fee now owns its own rules and can change without touching the
    //    other. The "free over $50" promo only ever existed for delivery.
    // ======================================================================

    static double deliveryFee(double distanceKm, double subtotal) {
        if (subtotal > 50) return 0.0; // free delivery promo — delivery only
        if (distanceKm <= 3) return 2.0;
        if (distanceKm <= 8) return 4.0;
        return 6.0;
    }

    static double orderServiceFee(double subtotal) {
        double fee = subtotal * 0.05;
        return Math.max(1.0, Math.min(fee, 10.0)); // always charged, no promo
    }

    // ======================================================================
    // 5) TODO EXERCISE (~5 minutes)
    //    calculateExtraCharge() below has the same problem brewing: a
    //    `type` flag controls two unrelated small charges — a packaging
    //    fee (based on order size) and a rush-hour surcharge (based on
    //    time of day). They only coincidentally both "return a small
    //    dollar amount," and they're about to diverge (imagine packaging
    //    soon needs a "fragile items" surcharge, rush needs a "driver
    //    shortage" multiplier — neither applies to the other).
    //
    //    Your task:
    //      a) Write packagingFee(double subtotal) and
    //         rushFee(boolean isPeakTime) as two independent methods.
    //      b) Update the calls in main() to use the two new methods
    //         instead of calculateExtraCharge(...).
    //      c) Delete calculateExtraCharge() once nothing calls it.
    //    Goal: recognize that "packaging" and "rush hour" only
    //    coincidentally share a shape today — don't force them to share
    //    one method just because it currently saves a few lines.
    // ======================================================================

    static double calculateExtraCharge(String type, double subtotal, boolean isPeakTime) {
        if (type.equals("PACKAGING")) {
            return subtotal < 20 ? 1.0 : 0.0;
        } else if (type.equals("RUSH")) {
            return isPeakTime ? 3.0 : 0.0;
        }
        throw new IllegalArgumentException("Unknown type: " + type);
    }

    public static void main(String[] args) {
        System.out.println("== Step 1: two independent methods, both correct ==");
        System.out.println("Delivery fee (5km):        $" + deliveryFeeV1(5));
        System.out.println("Order service fee ($30):   $" + orderServiceFeeV1(30));

        System.out.println();
        System.out.println("== Step 2: merged into one 'DRY' method — still looks fine ==");
        System.out.println("Delivery fee (5km):        $" + calculateFee(30, 5, true));
        System.out.println("Order service fee ($30):   $" + calculateFee(30, 5, false));

        System.out.println();
        System.out.println("== Step 3: a 'free delivery over $50' promo leaks into the service fee ==");
        System.out.println("Delivery fee ($80 order):      $" + calculateFeeV2(80, 5, true) + "  (correct — promo applies)");
        System.out.println("Order service fee ($80 order): $" + calculateFeeV2(80, 5, false) + "  (WRONG — should still be $4.00)");
        System.out.println("^ A promo meant only for delivery leaked into the service fee because");
        System.out.println("  both fees were forced to share one method.");

        System.out.println();
        System.out.println("== Step 4: the fix — split back into independent methods ==");
        System.out.println("Delivery fee ($80 order):      $" + deliveryFee(5, 80) + "  (free, as intended)");
        System.out.println("Order service fee ($80 order): $" + orderServiceFee(80) + "  (charged correctly)");

        System.out.println();
        System.out.println("== TODO exercise: split calculateExtraCharge() below ==");
        System.out.println("Packaging fee ($15 order): $" + calculateExtraCharge("PACKAGING", 15, false));
        System.out.println("Rush fee (peak time):      $" + calculateExtraCharge("RUSH", 15, true));
    }
}
