package codingstandards.readability.example;

import java.util.List;

/** Method size: length is a symptom, not the disease. Extract until each method is a named step. */
public class MethodSizeExamples {

    // BEFORE: one method, every step crammed together — you have to read all of it to trust any of it
    public static double checkoutTotalMonolithic(List<Double> prices, double taxRate, double discountRate) {
        double subtotal = 0;
        for (double price : prices) {
            subtotal += price;
        }
        double discounted = subtotal - (subtotal * discountRate);
        double tax = discounted * taxRate;
        return discounted + tax;
    }

    // AFTER: same logic, extracted into named steps — each one readable on its own
    public static double checkoutTotal(List<Double> prices, double taxRate, double discountRate) {
        double subtotal = sum(prices);
        double discounted = applyDiscount(subtotal, discountRate);
        double tax = calculateTax(discounted, taxRate);
        return discounted + tax;
    }

    private static double sum(List<Double> prices) {
        double total = 0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }

    private static double applyDiscount(double amount, double discountRate) {
        return amount - (amount * discountRate);
    }

    private static double calculateTax(double amount, double taxRate) {
        return amount * taxRate;
    }
}
