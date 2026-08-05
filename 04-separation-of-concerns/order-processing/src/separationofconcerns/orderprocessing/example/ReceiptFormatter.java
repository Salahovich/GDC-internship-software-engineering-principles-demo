package separationofconcerns.orderprocessing.example;

import java.util.List;

/** AFTER: SoC applied — the formatting concern, and only that concern. */
public class ReceiptFormatter {
    public static String format(String customerEmail, List<Item> items, double total) {
        StringBuilder receipt = new StringBuilder("Receipt for " + customerEmail + ":\n");
        for (Item item : items) {
            receipt.append(" - ").append(item.name()).append(" x").append(item.qty())
                    .append(" = $").append(item.price() * item.qty()).append("\n");
        }
        receipt.append("Total: $").append(total);
        return receipt.toString();
    }
}
