package designpatterns.proxy.inventoryproxy.example;

/** The interface callers depend on — they don't know or care if it's real or a proxy. */
public interface InventoryLookup {
    int getStock(String sku);
}
