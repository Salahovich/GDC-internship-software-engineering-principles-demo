package designpatterns.facade.checkoutfacade.example;

/**
 * AFTER: one simple method hides the three subsystems and gets the order
 * right — check stock, THEN charge, THEN notify.
 */
public class CheckoutFacade {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final NotificationService notifications = new NotificationService();

    public void placeOrder(String customerEmail, String sku, int quantity, double amount) {
        if (!inventory.hasStock(sku, quantity)) {
            System.out.println("[CheckoutFacade] Out of stock — nothing charged.");
            return;
        }
        inventory.reserve(sku, quantity);
        payment.charge(customerEmail, amount);
        notifications.sendOrderConfirmation(customerEmail);
    }
}
