package cohesionandcoupling.storeoperations.example;

/** AFTER: high cohesion, low coupling — the promotional email concern alone. */
public class PromotionNotifier {
    private final String emailSenderAddress;

    public PromotionNotifier(String emailSenderAddress) {
        this.emailSenderAddress = emailSenderAddress;
    }

    public void sendPromotionEmail(String customerEmail, String promoCode) {
        System.out.println("[" + emailSenderAddress + " -> " + customerEmail + "] Use code " + promoCode + " for 10% off!");
    }
}
