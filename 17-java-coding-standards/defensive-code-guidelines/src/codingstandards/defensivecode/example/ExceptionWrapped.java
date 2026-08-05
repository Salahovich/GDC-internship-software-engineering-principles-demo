package codingstandards.defensivecode.example;

/** AFTER: caught, and rethrown wrapped with context. */
public class ExceptionWrapped {
    public static int parseAmount(String raw) {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount: '" + raw + "'", e);
        }
    }
}
