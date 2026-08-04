package failfast.discountbatch;

import java.util.List;

/**
 * FAIL FAST
 * ----------------------------------------------------------------------
 * Detect and report a problem as soon as possible — right where it
 * happens — instead of letting bad data quietly flow deeper into the
 * system, where it eventually causes a confusing failure (or worse, a
 * silently wrong result) far from its real cause.
 *
 * This demo applies a discount to a batch of orders two ways: BEFORE, no
 * upfront check — an invalid discount silently produces a nonsensical
 * negative price for every order; AFTER, the discount is validated the
 * moment it's received, with a clear error pointing straight at the
 * problem.
 */
public class FailFastDemo {

    record Order(String id, double price) {}

    // ======================================================================
    // 1) BEFORE — violates fail fast.
    //    applyDiscountBatchLate() accepts whatever discount it's given
    //    and passes it all the way down to the arithmetic. An invalid
    //    discount (say, 150%) isn't rejected anywhere — it just produces
    //    a negative price, silently, for every order in the batch.
    // ======================================================================

    static double applyDiscountToPrice(double price, double discountPercent) {
        return price - (price * discountPercent / 100);
    }

    static void applyDiscountBatchLate(List<Order> orders, double discountPercent) {
        for (Order order : orders) {
            double discounted = applyDiscountToPrice(order.price(), discountPercent);
            System.out.println(order.id() + ": $" + order.price() + " -> $" + discounted);
        }
    }

    // ======================================================================
    // 2) AFTER — fail fast applied.
    //    The discount is validated ONCE, at the entry point, before
    //    touching a single order. An invalid discount fails immediately
    //    with a message that points straight at the problem — not
    //    several orders later, and never as a silently wrong negative
    //    price.
    // ======================================================================

    static void validateDiscountPercent(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(
                    "discountPercent must be between 0 and 100, got: " + discountPercent);
        }
    }

    static void applyDiscountBatch(List<Order> orders, double discountPercent) {
        validateDiscountPercent(discountPercent);
        for (Order order : orders) {
            double discounted = applyDiscountToPrice(order.price(), discountPercent);
            System.out.println(order.id() + ": $" + order.price() + " -> $" + discounted);
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    applyShippingRateLate() below has the same problem: an invalid
    //    shipping rate (e.g., negative) isn't checked anywhere — it
    //    silently produces a negative shipping charge deep inside the
    //    loop, for every order.
    //
    //    Your task:
    //      a) Write validateShippingRate(double ratePerOrder) that
    //         throws IllegalArgumentException if ratePerOrder < 0.
    //      b) Write applyShippingRate(orders, ratePerOrder) that calls
    //         the validator FIRST, then does the same loop.
    //      c) Update main() to call your fail-fast version instead, then
    //         delete applyShippingRateLate().
    // ======================================================================

    static void applyShippingRateLate(List<Order> orders, double ratePerOrder) {
        for (Order order : orders) {
            double withShipping = order.price() + ratePerOrder;
            System.out.println(order.id() + ": shipping added -> $" + withShipping);
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(new Order("A1", 100.0), new Order("A2", 40.0));

        System.out.println("== BEFORE: bad discount silently produces negative prices ==");
        applyDiscountBatchLate(orders, 150); // 150% discount is nonsense, but nothing stops it

        System.out.println();
        System.out.println("== AFTER: bad discount is rejected immediately, before any order is touched ==");
        try {
            applyDiscountBatch(orders, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected immediately: " + e.getMessage());
        }

        System.out.println();
        System.out.println("== TODO exercise: make applyShippingRateLate() fail fast ==");
        applyShippingRateLate(orders, -5); // negative shipping rate — should never reach here unvalidated
    }
}
