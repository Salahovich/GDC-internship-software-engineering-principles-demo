package designpatterns.facade.checkoutfacade.example;

/** One of three subsystems checkout has to coordinate. */
public class PaymentService {
    public void charge(String customerEmail, double amount) {
        System.out.println("[PaymentService] Charged " + customerEmail + " $" + amount);
    }
}
