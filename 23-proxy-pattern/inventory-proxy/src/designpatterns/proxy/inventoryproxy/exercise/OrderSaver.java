package designpatterns.proxy.inventoryproxy.exercise;

/** Given: the interface callers depend on for saving orders. */
public interface OrderSaver {
    void save(String orderId);
}
