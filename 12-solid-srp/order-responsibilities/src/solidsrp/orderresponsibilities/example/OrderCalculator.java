package solidsrp.orderresponsibilities.example;

/** AFTER: SRP applied — one reason to change: the pricing rule. */
public class OrderCalculator {
    public double calculateTotal(Order order) {
        return order.getSubtotal();
    }
}
