package codingstandards.readability.example;

/**
 * READABILITY GUIDELINES
 * ----------------------------------------------------------------------
 * Three small, related rules: name things for what they hold/do, keep
 * methods short enough to hold in your head, and replace magic numbers
 * with names. Java coding standards slides 28, 29, 35.
 */
public class ReadabilityGuidelinesDemo {

    public static void main(String[] args) {
        System.out.println("== Naming: BEFORE vs AFTER ==");
        System.out.println("process(45)       -> " + NamingExamples.process(45) + "  (process what? 45 what?)");
        System.out.println("hasExpired(45)     -> " + NamingExamples.hasExpired(45) + "  (an overdue-by-45-days payment)");
        System.out.println("^ Same boolean, but only one of them tells you what it means without a comment.");

        System.out.println();
        System.out.println("== Method size: BEFORE vs AFTER ==");
        java.util.List<Double> prices = java.util.List.of(20.0, 15.0, 5.0);
        double before = MethodSizeExamples.checkoutTotalMonolithic(prices, 0.08, 0.10);
        double after = MethodSizeExamples.checkoutTotal(prices, 0.08, 0.10);
        System.out.println("checkoutTotalMonolithic(...) -> $" + before);
        System.out.println("checkoutTotal(...)           -> $" + after);
        System.out.println("^ Same total, but AFTER reads as 3 named steps (sum, discount, tax) instead of one block.");

        System.out.println();
        System.out.println("== Magic numbers: BEFORE vs AFTER ==");
        System.out.println("isClearedMagicNumber(1) -> " + MagicNumberExamples.isClearedMagicNumber(1) + "  (is 1 CLEARED? PENDING? you have to check)");
        System.out.println("isCleared(CLEARED)      -> " + MagicNumberExamples.isCleared(MagicNumberExamples.Status.CLEARED) + "  (the name is the documentation)");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/NullSafetyTodo.java ==");
    }
}
