package designpatterns.prototype.ordertemplate.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Given:
 *
 *   public class Quote {
 *       private String customerEmail;
 *       private List<String> items;
 *       private double discountRate;
 *       // constructor + getters
 *   }
 *
 * Sales wants to start from a saved "template" Quote and tweak it for
 * each new customer, without the template itself ever changing.
 *
 * Task: add a `copy()` method to `Quote` (in its own file, next to this
 * one) following the same shape as `Order.copy()` in the example
 * package: return a new `Quote` with the same customerEmail and
 * discountRate, but a NEW list built from the original items (not the
 * same List reference).
 *
 * Then, in a small main(), copy a template Quote, add an item to the
 * copy, and confirm the template's item list is unchanged.
 */
public class QuoteCopyTodo {
}
