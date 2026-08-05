package dryprinciple.pitfall.example;

import dryprinciple.pitfall.before.MergedFeeBefore;

import dryprinciple.pitfall.exercise.ExtraChargeTangled;

/**
 * DRY PRINCIPLE — PART 2: THE PITFALL (a.k.a. "the wrong abstraction")
 * ----------------------------------------------------------------------
 * DRY is about not repeating the same KNOWLEDGE/RULE. It is easy to
 * over-apply: two pieces of code that merely LOOK similar today, but
 * represent different rules that happen to coincide, are NOT duplication.
 * Forcing them to share one method just because they currently look alike
 * creates a false abstraction — and every future change to either flow
 * risks breaking the other one.
 */
public class DryPitfallDemo {

    public static void main(String[] args) {
        System.out.println("== Step 1: two independent methods, both correct ==");
        System.out.println("Delivery fee (5km):        $" + MergedFeeBefore.deliveryFeeV1(5));
        System.out.println("Order service fee ($30):   $" + MergedFeeBefore.orderServiceFeeV1(30));

        System.out.println();
        System.out.println("== Step 2: merged into one 'DRY' method — still looks fine ==");
        System.out.println("Delivery fee (5km):        $" + MergedFeeBefore.calculateFee(30, 5, true));
        System.out.println("Order service fee ($30):   $" + MergedFeeBefore.calculateFee(30, 5, false));

        System.out.println();
        System.out.println("== Step 3: a 'free delivery over $50' promo leaks into the service fee ==");
        System.out.println("Delivery fee ($80 order):      $" + MergedFeeBefore.calculateFeeV2(80, 5, true) + "  (correct — promo applies)");
        System.out.println("Order service fee ($80 order): $" + MergedFeeBefore.calculateFeeV2(80, 5, false) + "  (WRONG — should still be $4.00)");
        System.out.println("^ A promo meant only for delivery leaked into the service fee because");
        System.out.println("  both fees were forced to share one method.");

        System.out.println();
        System.out.println("== Step 4: the fix — split back into independent methods ==");
        System.out.println("Delivery fee ($80 order):      $" + SplitFeeAfter.deliveryFee(5, 80) + "  (free, as intended)");
        System.out.println("Order service fee ($80 order): $" + SplitFeeAfter.orderServiceFee(80) + "  (charged correctly)");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/ExtraChargeTangled.java ==");
        System.out.println("Packaging fee ($15 order): $" + ExtraChargeTangled.calculateExtraCharge("PACKAGING", 15, false));
        System.out.println("Rush fee (peak time):      $" + ExtraChargeTangled.calculateExtraCharge("RUSH", 15, true));
    }
}
