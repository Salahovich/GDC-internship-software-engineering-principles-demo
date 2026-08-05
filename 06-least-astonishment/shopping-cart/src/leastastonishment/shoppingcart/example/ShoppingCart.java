package leastastonishment.shoppingcart.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AFTER: least astonishment applied. getItemsSortedByPrice() returns a
 * NEW sorted copy. The cart's own order is untouched, and the caller
 * can freely modify the returned list without corrupting the cart's
 * internal state either (getItems() also returns a defensive copy, for
 * the same reason).
 */
public class ShoppingCart {
    private final List<String> items =
            new ArrayList<>(List.of("Keyboard($60)", "Mouse($25)", "Monitor($200)"));

    public List<String> getItems() {
        return List.copyOf(items); // defensive copy — no hidden aliasing either
    }

    public List<String> getItemsSortedByPrice() {
        List<String> copy = new ArrayList<>(items);
        Collections.sort(copy);
        return copy; // original cart order is untouched
    }
}
