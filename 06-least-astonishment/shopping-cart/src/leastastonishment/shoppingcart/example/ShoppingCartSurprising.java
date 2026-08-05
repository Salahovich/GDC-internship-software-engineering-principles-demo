package leastastonishment.shoppingcart.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BEFORE: violates least astonishment. getItemsSortedByPriceSurprising()
 * looks like an innocent getter, but it sorts the cart's OWN internal
 * list in place before returning it. Any caller who just wanted "a
 * sorted view for display" has permanently reordered the actual cart —
 * checkout order, receipt order, everything downstream is now
 * different, with nothing in the method's name warning that would
 * happen.
 */
public class ShoppingCartSurprising {
    private final List<String> items =
            new ArrayList<>(List.of("Keyboard($60)", "Mouse($25)", "Monitor($200)"));

    public List<String> getItems() {
        return items;
    }

    // Looks read-only. Isn't.
    public List<String> getItemsSortedByPriceSurprising() {
        Collections.sort(items); // mutates the field in place!
        return items;
    }
}
