package designpatterns.adapter.paymentadapter.example;

/**
 * BEFORE (the problem): a third-party/legacy library with an incompatible
 * method name and a different unit (cents, not dollars). We can't change
 * this class — it ships in a jar we don't own.
 */
public class LegacyPaymentGateway {
    public void submitPaymentInCents(long amountInCents) {
        System.out.println("[LegacyPaymentGateway] Submitted " + amountInCents + " cents");
    }
}
