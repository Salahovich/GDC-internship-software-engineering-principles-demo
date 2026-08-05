package codingstandards.readability.before;

import java.util.List;

/** BEFORE: one method, every step crammed together — you have to read all of it to trust any of it. */
public class MethodSizeMonolithic {
    public static double checkoutTotalMonolithic(List<Double> prices, double taxRate, double discountRate) {
        double subtotal = 0;
        for (double price : prices) {
            subtotal += price;
        }
        double discounted = subtotal - (subtotal * discountRate);
        double tax = discounted * taxRate;
        return discounted + tax;
    }
}
