package failfast.discountbatch.exercise;

import failfast.discountbatch.example.Order;

import java.util.List;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * applyShippingRateLate() below has the same problem: an invalid
 * shipping rate (e.g., negative) isn't checked anywhere — it silently
 * produces a negative shipping charge deep inside the loop, for every
 * order.
 *
 * Task:
 *   a) Write validateShippingRate(double ratePerOrder) that throws
 *      IllegalArgumentException if ratePerOrder < 0.
 *   b) Write applyShippingRate(orders, ratePerOrder) that calls the
 *      validator FIRST, then does the same loop.
 *   c) Update FailFastDemo.main() to call your fail-fast version
 *      instead, then delete this class.
 */
public class ShippingRateLate {

    public static void applyShippingRateLate(List<Order> orders, double ratePerOrder) {
        for (Order order : orders) {
            double withShipping = order.price() + ratePerOrder;
            System.out.println(order.id() + ": shipping added -> $" + withShipping);
        }
    }
}
