package failfast.discountbatch.example;

import failfast.discountbatch.before.DiscountBatchLate;

import failfast.discountbatch.exercise.ShippingRateLate;

import java.util.List;

/**
 * FAIL FAST
 * ----------------------------------------------------------------------
 * Detect and report a problem as soon as possible — right where it
 * happens — instead of letting bad data quietly flow deeper into the
 * system, where it eventually causes a confusing failure (or worse, a
 * silently wrong result) far from its real cause.
 */
public class FailFastDemo {

    public static void main(String[] args) {
        List<Order> orders = List.of(new Order("A1", 100.0), new Order("A2", 40.0));

        System.out.println("== BEFORE: bad discount silently produces negative prices ==");
        DiscountBatchLate.applyDiscountBatchLate(orders, 150); // 150% discount is nonsense, but nothing stops it

        System.out.println();
        System.out.println("== AFTER: bad discount is rejected immediately, before any order is touched ==");
        try {
            DiscountBatch.applyDiscountBatch(orders, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected immediately: " + e.getMessage());
        }

        System.out.println();
        System.out.println("== TODO exercise: see exercise/ShippingRateLate.java ==");
        ShippingRateLate.applyShippingRateLate(orders, -5); // negative shipping rate — should never reach here unvalidated
    }
}
