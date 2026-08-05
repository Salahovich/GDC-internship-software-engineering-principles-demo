package codingstandards.defensivecode.example;

import codingstandards.defensivecode.before.ExceptionSwallowed;
import codingstandards.defensivecode.before.FeeLookupNullBefore;
import codingstandards.defensivecode.before.OrderIdBroken;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * DEFENSIVE CODE GUIDELINES
 * ----------------------------------------------------------------------
 * Three small, related rules: never swallow an exception, use Optional
 * as a return type (not a field), and override equals()/hashCode()
 * together or not at all. Java coding standards slides 30, 31, 33.
 */
public class DefensiveCodeGuidelinesDemo {

    public static void main(String[] args) {
        System.out.println("== Exceptions: BEFORE vs AFTER ==");
        System.out.println("parseAmountSwallowed(\"abc\") -> " + ExceptionSwallowed.parseAmountSwallowed("abc")
                + "  (looks like a valid $0 amount — the real problem vanished)");
        try {
            ExceptionWrapped.parseAmount("abc");
        } catch (IllegalArgumentException e) {
            System.out.println("parseAmount(\"abc\") threw: " + e.getMessage() + "  (the real problem is visible)");
        }

        System.out.println();
        System.out.println("== Optional: BEFORE vs AFTER ==");
        Fee missing = FeeLookupNullBefore.findFeeOrNullBefore("STANDARD");
        System.out.println("findFeeOrNullBefore(\"STANDARD\") -> " + missing);
        try {
            System.out.println("missing.amount() -> " + missing.amount());
        } catch (NullPointerException e) {
            System.out.println("^ NullPointerException — nothing forced the caller to check for null first.");
        }
        Optional<Fee> safe = FeeLookupOptionalAfter.findFeeAfter("STANDARD");
        System.out.println("findFeeAfter(\"STANDARD\").map(Fee::amount).orElse(0.0) -> "
                + safe.map(Fee::amount).orElse(0.0) + "  (absence handled, not ignored)");

        System.out.println();
        System.out.println("== Equals & hashCode: BEFORE vs AFTER ==");
        Set<OrderIdBroken> brokenSet = new HashSet<>();
        brokenSet.add(new OrderIdBroken("A1"));
        System.out.println("brokenSet.contains(new OrderIdBroken(\"A1\")) -> "
                + brokenSet.contains(new OrderIdBroken("A1"))
                + "  (equal by equals(), but hashCode() sent it to a different bucket)");

        Set<OrderId> fixedSet = new HashSet<>();
        fixedSet.add(new OrderId("A1"));
        System.out.println("fixedSet.contains(new OrderId(\"A1\"))       -> "
                + fixedSet.contains(new OrderId("A1"))
                + "  (both overridden consistently — found)");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/SkuCodeHashCodeTodo.java ==");
    }
}
