package yagniprinciple.customerfields;

import java.util.List;
import java.util.Map;

/**
 * YAGNI PRINCIPLE ("You Aren't Gonna Need It")
 * ----------------------------------------------------------------------
 * Don't add functionality — fields, parameters, config, abstraction —
 * until a real requirement needs it. "We might need this later"
 * speculation adds maintenance cost (more fields to fill in, more null
 * checks, bigger constructors) today, for a feature that may never ship,
 * designed against a guess instead of a real spec.
 *
 * This demo shows a Customer class bloated with fields no current
 * feature reads (BEFORE), then trimmed to exactly what today's feature
 * needs (AFTER).
 */
public class YagniDemo {

    // ======================================================================
    // 1) BEFORE — violates YAGNI.
    //    "We might add a loyalty program / referrals / i18n / (really)
    //    horoscopes later" — none of these are real requirements today,
    //    yet every one of them is a field someone has to populate (often
    //    with null or "unknown") at every call site, forever.
    // ======================================================================

    static class CustomerOverEngineered {
        String name;
        String email;

        // Speculative fields — no current feature reads any of these.
        String loyaltyTier;                 // "in case we add a loyalty program"
        String referralCode;                // "in case we add referrals"
        String preferredLanguage;           // "in case we go international"
        String birthDate;                   // "in case marketing wants horoscopes" (really)
        Map<String, Object> extraMetadata;  // "future-proofing" — never read

        CustomerOverEngineered(String name, String email, String loyaltyTier,
                                String referralCode, String preferredLanguage,
                                String birthDate, Map<String, Object> extraMetadata) {
            this.name = name;
            this.email = email;
            this.loyaltyTier = loyaltyTier;
            this.referralCode = referralCode;
            this.preferredLanguage = preferredLanguage;
            this.birthDate = birthDate;
            this.extraMetadata = extraMetadata;
        }
    }

    // The one feature that exists today only ever touches 2 of the 7
    // constructor arguments above — the other 5 are dead weight that
    // still has to be filled in (usually with null) at every call site.
    static String buildConfirmationEmail(CustomerOverEngineered customer, String orderId) {
        return "Hi " + customer.name + ", your order " + orderId + " is confirmed. "
                + "A receipt has been sent to " + customer.email + ".";
    }

    // ======================================================================
    // 2) AFTER — YAGNI applied.
    //    Only the fields today's feature actually needs. If a loyalty
    //    program becomes a real requirement, add loyaltyTier THEN — with
    //    a real spec to design it against, not a guess.
    // ======================================================================

    record Customer(String name, String email) {}

    static String buildConfirmationEmail(Customer customer, String orderId) {
        return "Hi " + customer.name() + ", your order " + orderId + " is confirmed. "
                + "A receipt has been sent to " + customer.email() + ".";
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    OrderOverEngineered below has the same problem: three fields
    //    ("estimatedCarbonFootprint", "vipPriorityFlag",
    //    "giftWrapNoteHistory") were added speculatively and are never
    //    read by calculateTotal() — the only method that exists today.
    //
    //    Your task:
    //      a) Create a trimmed-down `Order` (a record works well) with
    //         only the fields calculateTotal actually uses: itemPrices
    //         and discountPercent.
    //      b) Write a calculateTotal overload (or replacement) that takes
    //         your new Order type and update the call in main().
    //      c) Delete OrderOverEngineered once nothing calls it.
    //    Goal: don't design for hypothetical future requirements — add a
    //    field when a real feature needs it, not before.
    // ======================================================================

    static class OrderOverEngineered {
        List<Double> itemPrices;
        double discountPercent;

        // Speculative — nothing in the codebase reads these today.
        double estimatedCarbonFootprint;
        boolean vipPriorityFlag;
        List<String> giftWrapNoteHistory;

        OrderOverEngineered(List<Double> itemPrices, double discountPercent,
                             double estimatedCarbonFootprint, boolean vipPriorityFlag,
                             List<String> giftWrapNoteHistory) {
            this.itemPrices = itemPrices;
            this.discountPercent = discountPercent;
            this.estimatedCarbonFootprint = estimatedCarbonFootprint;
            this.vipPriorityFlag = vipPriorityFlag;
            this.giftWrapNoteHistory = giftWrapNoteHistory;
        }
    }

    static double calculateTotal(OrderOverEngineered order) {
        double subtotal = order.itemPrices.stream().mapToDouble(Double::doubleValue).sum();
        return subtotal * (1 - order.discountPercent / 100);
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: Customer has 5 speculative fields, only 2 ever used ==");
        CustomerOverEngineered bloated = new CustomerOverEngineered(
                "Amina", "amina@example.com",
                null, null, null, null, null); // 5 of 7 args are "we don't know yet"
        System.out.println(buildConfirmationEmail(bloated, "ORD-1001"));

        System.out.println();
        System.out.println("== AFTER: Customer has exactly what today's feature needs ==");
        Customer trimmed = new Customer("Amina", "amina@example.com");
        System.out.println(buildConfirmationEmail(trimmed, "ORD-1001"));

        System.out.println();
        System.out.println("== TODO exercise: trim down OrderOverEngineered below ==");
        OrderOverEngineered order = new OrderOverEngineered(
                List.of(19.99, 34.50), 10.0,
                0.0, false, List.of()); // 3 of 5 fields are unused padding
        System.out.println("Total: $" + calculateTotal(order));
    }
}
