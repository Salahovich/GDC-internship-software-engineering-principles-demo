package failfast.discountbatch.example;

import failfast.discountbatch.before.DiscountBatchLate;

import java.util.List;

/**
 * AFTER: fail fast applied. The discount is validated ONCE, at the entry
 * point, before touching a single order. An invalid discount fails
 * immediately with a message that points straight at the problem — not
 * several orders later, and never as a silently wrong negative price.
 */
public class DiscountBatch {

    public static void validateDiscountPercent(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(
                    "discountPercent must be between 0 and 100, got: " + discountPercent);
        }
    }

    public static void applyDiscountBatch(List<Order> orders, double discountPercent) {
        validateDiscountPercent(discountPercent);
        for (Order order : orders) {
            double discounted = DiscountBatchLate.applyDiscountToPrice(order.price(), discountPercent);
            System.out.println(order.id() + ": $" + order.price() + " -> $" + discounted);
        }
    }
}
