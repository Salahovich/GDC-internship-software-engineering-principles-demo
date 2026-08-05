package yagniprinciple.customerfields.before;

import java.util.Map;

/**
 * BEFORE: violates YAGNI. "We might add a loyalty program / referrals /
 * i18n / (really) horoscopes later" — none of these are real
 * requirements today, yet every one of them is a field someone has to
 * populate (often with null or "unknown") at every call site, forever.
 */
public class CustomerOverEngineered {
    public String name;
    public String email;

    // Speculative fields — no current feature reads any of these.
    public String loyaltyTier;                 // "in case we add a loyalty program"
    public String referralCode;                // "in case we add referrals"
    public String preferredLanguage;            // "in case we go international"
    public String birthDate;                    // "in case marketing wants horoscopes" (really)
    public Map<String, Object> extraMetadata;   // "future-proofing" — never read

    public CustomerOverEngineered(String name, String email, String loyaltyTier,
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

    // The one feature that exists today only ever touches 2 of the 7
    // constructor arguments above — the other 5 are dead weight that
    // still has to be filled in (usually with null) at every call site.
    public static String buildConfirmationEmail(CustomerOverEngineered customer, String orderId) {
        return "Hi " + customer.name + ", your order " + orderId + " is confirmed. "
                + "A receipt has been sent to " + customer.email + ".";
    }
}
