package anemicvsrich.orderdomainmodel.example;

import java.util.ArrayList;
import java.util.List;

/**
 * AFTER: rich domain model. Order enforces its own rule inside
 * applyDiscount(). There is no setDiscountPercent() to bypass — the
 * only way to change the discount is through the method that validates
 * it.
 */
public class Order {
    private final List<Double> itemPrices = new ArrayList<>();
    private double discountPercent;

    public void addItem(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        itemPrices.add(price);
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }
        this.discountPercent = percent;
    }

    public double getTotal() {
        double subtotal = itemPrices.stream().mapToDouble(Double::doubleValue).sum();
        return subtotal * (1 - discountPercent / 100);
    }
}
