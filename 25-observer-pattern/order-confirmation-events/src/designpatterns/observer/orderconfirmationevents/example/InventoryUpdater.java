package designpatterns.observer.orderconfirmationevents.example;

public class InventoryUpdater implements OrderListener {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("[InventoryUpdater] Reserved stock for " + order.getCustomerEmail());
    }
}
