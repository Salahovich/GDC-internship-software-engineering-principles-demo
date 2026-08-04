package codingstandards.modernjava.example;

/** Logging: log for the person debugging this at 3am with no access to your IDE. */
public class LoggingExamples {

    // BEFORE: wrong level for a real failure, no searchable identifier, and a logged secret
    public static void processPaymentBadLogging(String declarationId, String cardToken) {
        System.out.println("[DEBUG] something happened");
        System.out.println("[INFO] card token: " + cardToken);
    }

    // AFTER: right level, a searchable identifier, nothing sensitive
    public static void processPaymentGoodLogging(String declarationId) {
        System.out.println("[ERROR] Payment failed for declaration " + declarationId + " — gateway timeout");
    }
}
