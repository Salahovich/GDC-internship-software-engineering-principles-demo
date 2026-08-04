package designpatterns.proxy.inventoryproxy.example;

/**
 * PROXY PATTERN
 * ----------------------------------------------------------------------
 * A stand-in that implements the same interface as the real object, and
 * adds behavior (caching, access control, logging...) around it without
 * the caller knowing the difference.
 */
public class ProxyDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: calling RealInventoryService directly ==");
        RealInventoryService realService = new RealInventoryService();
        realService.getStock("MUG-01");
        realService.getStock("MUG-01");
        realService.getStock("MUG-01");
        System.out.println("Real service was queried " + realService.getCallCount() + " times for the same SKU.");

        System.out.println();
        System.out.println("== AFTER: calling through CachingInventoryProxy ==");
        RealInventoryService realService2 = new RealInventoryService();
        InventoryLookup proxy = new CachingInventoryProxy(realService2);
        proxy.getStock("MUG-01");
        proxy.getStock("MUG-01");
        proxy.getStock("MUG-01");
        System.out.println("Real service was queried " + realService2.getCallCount() + " time(s) — the rest hit the cache.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/LoggingProxyTodo.java ==");
    }
}
