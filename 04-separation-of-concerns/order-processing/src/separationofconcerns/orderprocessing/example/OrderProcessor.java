package separationofconcerns.orderprocessing.example;

import java.util.List;

/** AFTER: orchestrates the four focused pieces. No logic of its own to break. */
public class OrderProcessor {
    public static void processOrder(String customerEmail, List<Item> items) {
        OrderValidator.validate(customerEmail, items);
        double total = PriceCalculator.calculateTotal(items);
        String receipt = ReceiptFormatter.format(customerEmail, items, total);
        NotificationService.send(customerEmail, receipt);
    }
}
