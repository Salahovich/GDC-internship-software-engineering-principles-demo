package designpatterns.adapter.paymentadapter.example;

/** AFTER: wraps the legacy gateway so it can be used wherever a PaymentProcessor is expected. */
public class LegacyPaymentGatewayAdapter implements PaymentProcessor {
    private final LegacyPaymentGateway legacyGateway;

    public LegacyPaymentGatewayAdapter(LegacyPaymentGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public void pay(double amount) {
        long amountInCents = Math.round(amount * 100);
        legacyGateway.submitPaymentInCents(amountInCents);
    }
}
