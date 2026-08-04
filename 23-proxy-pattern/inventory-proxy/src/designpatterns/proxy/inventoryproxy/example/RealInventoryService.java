package designpatterns.proxy.inventoryproxy.example;

/** BEFORE (the real subject): slow — pretend every call hits a database or network. */
public class RealInventoryService implements InventoryLookup {
    private int callCount = 0;

    @Override
    public int getStock(String sku) {
        callCount++;
        System.out.println("[RealInventoryService] Querying database for " + sku + " (slow!)");
        return 42;
    }

    public int getCallCount() {
        return callCount;
    }
}
