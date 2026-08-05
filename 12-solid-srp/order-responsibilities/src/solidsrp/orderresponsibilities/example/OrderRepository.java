package solidsrp.orderresponsibilities.example;

/** AFTER: SRP applied — one reason to change: how orders are persisted. */
public class OrderRepository {
    public void save(Order order) {
        System.out.println("[DB] saved order for " + order.getCustomerEmail());
    }
}
