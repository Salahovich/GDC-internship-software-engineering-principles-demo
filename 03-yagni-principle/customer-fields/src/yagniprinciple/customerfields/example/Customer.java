package yagniprinciple.customerfields.example;

/**
 * AFTER: YAGNI applied. Only the fields today's feature actually needs.
 * If a loyalty program becomes a real requirement, add loyaltyTier
 * THEN — with a real spec to design it against, not a guess.
 */
public record Customer(String name, String email) {

    public static String buildConfirmationEmail(Customer customer, String orderId) {
        return "Hi " + customer.name() + ", your order " + orderId + " is confirmed. "
                + "A receipt has been sent to " + customer.email() + ".";
    }
}
