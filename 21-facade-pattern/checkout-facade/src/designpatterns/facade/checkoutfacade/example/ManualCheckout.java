package designpatterns.facade.checkoutfacade.example;

/**
 * BEFORE: client code has to know all three subsystems, call them in the
 * right order, and remember to check stock BEFORE charging the card.
 */
public class ManualCheckout {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final NotificationService notifications = new NotificationService();

    public void placeOrderTheHardWay(String customerEmail, String sku, int quantity, double amount) {
        // Bug: charges the card BEFORE checking whether the item is in stock.
        payment.charge(customerEmail, amount);
        if (inventory.hasStock(sku, quantity)) {
            inventory.reserve(sku, quantity);
            notifications.sendOrderConfirmation(customerEmail);
        } else {
            System.out.println("[ManualCheckout] Out of stock — but we already charged the card!");
        }
    }
}
