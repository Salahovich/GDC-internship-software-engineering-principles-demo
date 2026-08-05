package separationofconcerns.orderprocessing.example;

import java.util.List;

/**
 * BEFORE: violates SoC. One method does validation, math, string
 * formatting, AND "sending" (here, printing) all tangled together. Want
 * to change the receipt wording? You're editing the same method that
 * also validates input and computes totals — easy to break one while
 * touching the other.
 */
public class OrderProcessingTangled {

    public static void processOrderTangled(String customerEmail, List<Item> items) {
        // -- validation concern --
        if (customerEmail == null || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("invalid email: " + customerEmail);
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("order must contain at least one item");
        }

        // -- calculation concern --
        double total = 0;
        for (Item item : items) {
            total += item.price() * item.qty();
        }

        // -- formatting concern --
        StringBuilder receipt = new StringBuilder("Receipt for " + customerEmail + ":\n");
        for (Item item : items) {
            receipt.append(" - ").append(item.name()).append(" x").append(item.qty())
                    .append(" = $").append(item.price() * item.qty()).append("\n");
        }
        receipt.append("Total: $").append(total);

        // -- "sending" concern --
        System.out.println(receipt);
        System.out.println("[sent to " + customerEmail + "]");
    }
}
