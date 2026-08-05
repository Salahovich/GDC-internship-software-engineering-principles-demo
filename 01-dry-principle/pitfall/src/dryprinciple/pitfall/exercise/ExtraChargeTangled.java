package dryprinciple.pitfall.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * calculateExtraCharge() below has the same problem brewing: a `type`
 * flag controls two unrelated small charges — a packaging fee (based on
 * order size) and a rush-hour surcharge (based on time of day). They
 * only coincidentally both "return a small dollar amount," and they're
 * about to diverge (imagine packaging soon needs a "fragile items"
 * surcharge, rush needs a "driver shortage" multiplier — neither
 * applies to the other).
 *
 * Task:
 *   a) Write packagingFee(double subtotal) and rushFee(boolean isPeakTime)
 *      as two independent methods (in their own file).
 *   b) Update the calls in DryPitfallDemo.main() to use the two new
 *      methods instead of calculateExtraCharge(...).
 *   c) Delete this class once nothing calls it.
 *
 * Goal: recognize that "packaging" and "rush hour" only coincidentally
 * share a shape today — don't force them to share one method just
 * because it currently saves a few lines.
 */
public class ExtraChargeTangled {

    public static double calculateExtraCharge(String type, double subtotal, boolean isPeakTime) {
        if (type.equals("PACKAGING")) {
            return subtotal < 20 ? 1.0 : 0.0;
        } else if (type.equals("RUSH")) {
            return isPeakTime ? 3.0 : 0.0;
        }
        throw new IllegalArgumentException("Unknown type: " + type);
    }
}
