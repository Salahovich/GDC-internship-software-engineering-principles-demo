package codingstandards.modernjava.example;

/** AFTER: right level, a searchable identifier, nothing sensitive. */
public class LoggingGood {
    public static void processPaymentGoodLogging(String declarationId) {
        System.out.println("[ERROR] Payment failed for declaration " + declarationId + " — gateway timeout");
    }
}
