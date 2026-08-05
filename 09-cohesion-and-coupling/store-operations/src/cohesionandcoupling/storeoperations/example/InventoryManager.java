package cohesionandcoupling.storeoperations.example;

import java.util.HashMap;
import java.util.Map;

/** AFTER: high cohesion, low coupling — the inventory concern alone. */
public class InventoryManager {
    private final Map<String, Integer> stock = new HashMap<>();

    public void restockItem(String item, int qty) {
        stock.merge(item, qty, Integer::sum);
    }

    public int getStock(String item) {
        return stock.getOrDefault(item, 0);
    }
}
