package designpatterns.facade.checkoutfacade.example;

/** One of three subsystems checkout has to coordinate. */
public class NotificationService {
    public void sendOrderConfirmation(String customerEmail) {
        System.out.println("[NotificationService] Sent order confirmation to " + customerEmail);
    }
}
