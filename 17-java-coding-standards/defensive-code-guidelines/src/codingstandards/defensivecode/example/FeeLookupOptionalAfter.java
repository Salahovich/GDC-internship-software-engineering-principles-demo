package codingstandards.defensivecode.example;

import java.util.Optional;

/** AFTER: Optional makes "might be absent" part of the signature. */
public class FeeLookupOptionalAfter {
    public static Optional<Fee> findFeeAfter(String code) {
        return code.equals("RUSH") ? Optional.of(new Fee(code, 5.0)) : Optional.empty();
    }
}
