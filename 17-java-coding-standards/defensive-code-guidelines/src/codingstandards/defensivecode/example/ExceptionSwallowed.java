package codingstandards.defensivecode.example;

/** BEFORE: swallowed — the evidence of what went wrong is gone. */
public class ExceptionSwallowed {
    public static int parseAmountSwallowed(String raw) {
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            return 0; // silently wrong — the caller can't tell "$0" from "bad input"
        }
    }
}
