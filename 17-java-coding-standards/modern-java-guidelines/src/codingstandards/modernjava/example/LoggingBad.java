package codingstandards.modernjava.example;

/** BEFORE: wrong level for a real failure, no searchable identifier, and a logged secret. */
public class LoggingBad {
    public static void processPaymentBadLogging(String declarationId, String cardToken) {
        System.out.println("[DEBUG] something happened");
        System.out.println("[INFO] card token: " + cardToken);
    }
}
