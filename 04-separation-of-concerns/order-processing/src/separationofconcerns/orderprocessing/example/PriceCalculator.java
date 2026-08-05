package separationofconcerns.orderprocessing.example;

import java.util.List;

/** AFTER: SoC applied — the calculation concern, and only that concern. */
public class PriceCalculator {
    public static double calculateTotal(List<Item> items) {
        double total = 0;
        for (Item item : items) {
            total += item.price() * item.qty();
        }
        return total;
    }
}
