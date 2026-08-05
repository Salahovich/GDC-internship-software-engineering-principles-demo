package codingstandards.modernjava.example;

/**
 * AFTER: a record — constructor, accessors, equals, hashCode and
 * toString in one line, and there's no setter to accidentally call.
 */
public record MoneyRecordAfter(double amount, String currency) {}
