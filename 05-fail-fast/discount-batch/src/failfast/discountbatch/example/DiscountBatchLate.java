package failfast.discountbatch.example;

import java.util.List;

/**
 * BEFORE: violates fail fast. applyDiscountBatchLate() accepts whatever
 * discount it's given and passes it all the way down to the arithmetic.
 * An invalid discount (say, 150%) isn't rejected anywhere — it just
 * produces a negative price, silently, for every order in the batch.
 */
public class DiscountBatchLate {

    public static double applyDiscountToPrice(double price, double discountPercent) {
        return price - (price * discountPercent / 100);
    }

    public static void applyDiscountBatchLate(List<Order> orders, double discountPercent) {
        for (Order order : orders) {
            double discounted = applyDiscountToPrice(order.price(), discountPercent);
            System.out.println(order.id() + ": $" + order.price() + " -> $" + discounted);
        }
    }
}
