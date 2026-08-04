package designpatterns.observer.orderconfirmationevents.example;

/** BEFORE: knows every interested party by name. A new reaction means editing this class. */
public class OrderNotifierHardcoded {
    public void confirm(Order order) {
        new InventoryUpdater().onOrderConfirmed(order);
        new EmailSender().onOrderConfirmed(order);
    }
}
