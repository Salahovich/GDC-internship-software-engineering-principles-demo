package codingstandards.defensivecode.example;

/** Exceptions: handle it, or let it travel. Never swallow it. */
public class ExceptionHandlingExamples {

    // BEFORE: swallowed — the evidence of what went wrong is gone
    public static int parseAmountSwallowed(String raw) {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            return 0; // silently wrong — the caller can't tell "$0" from "bad input"
        }
    }

    // AFTER: caught, and rethrown wrapped with context
    public static int parseAmount(String raw) {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount: '" + raw + "'", e);
        }
    }
}
