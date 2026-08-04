package codingstandards.modernjava.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Given:
 *
 *   public class CouponMutable {
 *       private String code;
 *       private double discountPercent;
 *       public CouponMutable(String code, double discountPercent) { ... }
 *       public double getDiscountPercent() { return discountPercent; }
 *       public void setDiscountPercent(double discountPercent) { this.discountPercent = discountPercent; }
 *   }
 *
 * The same CouponMutable instance is shared by several parts of checkout
 * (cart summary, receipt, email). One of them "temporarily" adjusts
 * discountPercent for a display tweak and forgets to change it back —
 * now every holder of that coupon sees the wrong discount.
 *
 * Task: write `Coupon` (in its own file, next to this one) as a record
 * with `code` and `discountPercent` components — the same shape as
 * `Money` in the example package. Records have no setters, so this bug
 * class can't happen.
 *
 * Then, in a small main(), create a Coupon, confirm there's no way to
 * change discountPercent after construction (no setter exists to call),
 * and print it to see the free toString().
 */
public class CouponRecordTodo {
}
