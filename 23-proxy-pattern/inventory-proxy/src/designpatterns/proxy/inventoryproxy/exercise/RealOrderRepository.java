package designpatterns.proxy.inventoryproxy.exercise;

/** Given: the real subject — actually persists the order. */
public class RealOrderRepository implements OrderSaver {
    @Override
    public void save(String orderId) {
        System.out.println("[RealOrderRepository] Saved order " + orderId);
    }
}
