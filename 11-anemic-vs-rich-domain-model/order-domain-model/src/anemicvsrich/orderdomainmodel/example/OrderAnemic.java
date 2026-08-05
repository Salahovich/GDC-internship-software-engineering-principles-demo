package anemicvsrich.orderdomainmodel.example;

import java.util.ArrayList;
import java.util.List;

/**
 * BEFORE: anemic domain model. OrderAnemic is just a bag of fields. The
 * rule "discount must be 0-100" lives in OrderServiceAnemic, not in
 * OrderAnemic — so it only applies if every caller remembers to go
 * through the service. Any code with a reference to the order can call
 * setDiscountPercent() directly and skip the rule completely.
 */
public class OrderAnemic {
    private final List<Double> itemPrices = new ArrayList<>();
    private double discountPercent;

    public List<Double> getItemPrices() {
        return itemPrices;
    }

    public void addItemPrice(double price) {
        itemPrices.add(price);
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent; // no validation — it's just a bag of fields
    }

    public static class OrderServiceAnemic {
        public static void applyDiscount(OrderAnemic order, double percent) {
            // The rule lives HERE, outside the object it applies to.
            if (percent < 0 || percent > 100) {
                throw new IllegalArgumentException("discount must be between 0 and 100");
            }
            order.setDiscountPercent(percent);
        }

        public static double calculateTotal(OrderAnemic order) {
            double subtotal = order.getItemPrices().stream().mapToDouble(Double::doubleValue).sum();
            return subtotal * (1 - order.getDiscountPercent() / 100);
        }
    }
}
