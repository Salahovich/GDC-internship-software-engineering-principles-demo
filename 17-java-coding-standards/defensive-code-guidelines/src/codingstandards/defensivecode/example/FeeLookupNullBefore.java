package codingstandards.defensivecode.example;

/** BEFORE: null means "not found" — easy for a caller to forget to check. */
public class FeeLookupNullBefore {
    public static Fee findFeeOrNullBefore(String code) {
        return code.equals("RUSH") ? new Fee(code, 5.0) : null;
    }
}
