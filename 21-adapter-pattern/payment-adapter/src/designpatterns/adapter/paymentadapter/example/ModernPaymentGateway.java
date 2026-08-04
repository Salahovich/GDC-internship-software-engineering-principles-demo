package designpatterns.adapter.paymentadapter.example;

/** A gateway that already speaks PaymentProcessor's language. */
public class ModernPaymentGateway implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.println("[ModernPaymentGateway] Charged $" + amount);
    }
}
