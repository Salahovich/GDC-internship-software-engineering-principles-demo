package separationofconcerns.orderprocessing.example;

import java.util.List;

/** AFTER: SoC applied — the validation concern, and only that concern. */
public class OrderValidator {
    public static void validate(String customerEmail, List<Item> items) {
        if (customerEmail == null || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("invalid email: " + customerEmail);
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("order must contain at least one item");
        }
    }
}
