package designpatterns.builder.orderbuilder.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Shipping labels have the same telescoping problem as orders did. Given:
 *
 *   public class TelescopingShippingLabel {
 *       public TelescopingShippingLabel(String recipient, String address) { ... }
 *       public TelescopingShippingLabel(String recipient, String address, boolean fragile) { ... }
 *       public TelescopingShippingLabel(String recipient, String address, boolean fragile, String notes) { ... }
 *   }
 *
 * Task: write a `ShippingLabel` class (in its own file, next to this one)
 * with a nested static `Builder`, following the same shape as
 * `Order.Builder` in the example package:
 *   - required fields (recipient, address) taken by the Builder's
 *     constructor
 *   - optional fields (fragile, notes) set via chainable named methods
 *     (`fragile(boolean)`, `notes(String)`)
 *   - a `build()` method that returns the finished `ShippingLabel`
 *
 * Then build one in a small main() using the fluent style:
 *   new ShippingLabel.Builder("Amina", "123 Main St").fragile(true).notes("Leave at door").build();
 */
public class ShippingLabelBuilderTodo {
}
