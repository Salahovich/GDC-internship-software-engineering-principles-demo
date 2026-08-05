package codingstandards.readability.example;

import java.util.List;

/** AFTER: same logic, extracted into named steps — each one readable on its own. */
public class MethodSizeExtracted {
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
