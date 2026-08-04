package codingstandards.defensivecode.example;

import java.util.Optional;

/** Optional: a return type, not a field, not a parameter. */
public class OptionalExamples {

    public record Fee(String code, double amount) {}

    // BEFORE: null means "not found" — easy for a caller to forget to check
    public static Fee findFeeOrNullBefore(String code) {
        return code.equals("RUSH") ? new Fee(code, 5.0) : null;
    }

    // AFTER: Optional makes "might be absent" part of the signature
    public static Optional<Fee> findFeeAfter(String code) {
        return code.equals("RUSH") ? Optional.of(new Fee(code, 5.0)) : Optional.empty();
    }
}
