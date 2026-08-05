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
 * each new customer, without the template itself ever changing — same
 * problem the example package solves for orders (see Order's copy
 * constructor, and StandardOrder/GiftOrder).
 *
 * Task: turn `Quote` (in its own file, next to this one) into a
 * Prototype the same way:
 *
 *   1. Add a copy constructor: `protected Quote(Quote target)` that
 *      copies customerEmail and discountRate, and builds a NEW list
 *      from target's items (not the same List reference).
 *   2. Add `public Quote copy() { return new Quote(this); }`.
 *
 * Then, in a small main(), copy a template Quote, add an item to the
 * copy, and confirm the template's item list is unchanged — the same
 * independence check PrototypeDemo runs on orders.
 */
public class QuoteCopyTodo {
}
