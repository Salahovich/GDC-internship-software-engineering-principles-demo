package codingstandards.defensivecode.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Given:
 *
 *   public class SkuCode {
 *       private final String value;
 *       public SkuCode(String value) { this.value = value; }
 *       public boolean equals(Object o) {
 *           return o instanceof SkuCode other && other.value.equals(this.value);
 *       }
 *       // no hashCode() override
 *   }
 *
 * Warehouse code puts SkuCode instances into a HashSet to track which
 * SKUs have already been scanned today. Two SkuCode objects built from
 * the same string compare equal(), but `scannedToday.contains(...)`
 * still returns false for a SKU that was already added — same bug as
 * `OrderIdBroken` in the example package.
 *
 * Task: add a `hashCode()` override to `SkuCode` (in its own file, next
 * to this one) that's consistent with `equals()` — same shape as
 * `OrderId.hashCode()` in the example package.
 *
 * Then, in a small main(), add a SkuCode to a HashSet and confirm
 * `.contains(new SkuCode(sameValue))` returns true.
 */
public class SkuCodeHashCodeTodo {
}
