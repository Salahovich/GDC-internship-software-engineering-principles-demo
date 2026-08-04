package designpatterns.adapter.paymentadapter.example;

/** The interface checkout code is written against. */
public interface PaymentProcessor {
    void pay(double amount);
}
