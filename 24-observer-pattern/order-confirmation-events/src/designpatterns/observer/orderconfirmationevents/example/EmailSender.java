package designpatterns.observer.orderconfirmationevents.example;

public class EmailSender implements OrderListener {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("[EmailSender] Sent confirmation email to " + order.getCustomerEmail());
    }
}
