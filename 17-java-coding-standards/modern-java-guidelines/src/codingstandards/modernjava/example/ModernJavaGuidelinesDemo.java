package codingstandards.modernjava.example;

import java.util.List;

/**
 * MODERN JAVA GUIDELINES
 * ----------------------------------------------------------------------
 * Three small, related rules: prefer immutable data carriers (records),
 * keep streams linear and side-effect free, and log at the right level
 * with a searchable identifier. Java coding standards slides 32, 34, 36.
 */
public class ModernJavaGuidelinesDemo {

    public static void main(String[] args) {
        System.out.println("== Immutability & records: BEFORE vs AFTER ==");
        MoneyMutableBefore shared = new MoneyMutableBefore(19.99);
        System.out.println("before applyRoundingBug: " + shared.getAmount());
        MoneyMutableBefore.applyRoundingBug(shared);
        System.out.println("after applyRoundingBug:  " + shared.getAmount() + "  (changed — same object, no copy was made)");

        MoneyRecordAfter price = new MoneyRecordAfter(19.99, "USD");
        System.out.println("record toString(): " + price + "  (constructor, accessors, equals, hashCode, toString — free)");
        System.out.println("record equals():   " + price.equals(new MoneyRecordAfter(19.99, "USD")) + "  (value equality, also free)");
        System.out.println("^ MoneyRecordAfter has no setAmount() — the bug above can't exist here.");

        System.out.println();
        System.out.println("== Streams & loops: BEFORE vs AFTER ==");
        List<String> names = List.of("Amina", "Sam", "Youssef");
        System.out.println("upperCaseLongNamesUnclear(...) -> " + StreamsUnclear.upperCaseLongNamesUnclear(names));
        System.out.println("upperCaseLongNames(...)        -> " + StreamsClear.upperCaseLongNames(names));
        System.out.println("^ BEFORE comes back empty: count() doesn't need to visit any elements to answer");
        System.out.println("  \"how many\", so the stream is free to skip the map() step entirely — and the side");
        System.out.println("  effect inside it never runs. AFTER has no side effect to skip, so it's correct");
        System.out.println("  AND readable.");

        System.out.println();
        System.out.println("== Logging: BEFORE vs AFTER ==");
        System.out.println("-- bad logging --");
        LoggingBad.processPaymentBadLogging("DCL-4471", "tok_live_9f8a7b6c");
        System.out.println("^ Wrong level for a real failure, no declaration ID to search on, and a card token in the logs.");
        System.out.println("-- good logging --");
        LoggingGood.processPaymentGoodLogging("DCL-4471");
        System.out.println("^ ERROR means someone must act; DCL-4471 is searchable; nothing sensitive.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/CouponRecordTodo.java ==");
    }
}
