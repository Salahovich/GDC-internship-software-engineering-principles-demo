package designpatterns.proxy.inventoryproxy.example;

import java.util.HashMap;
import java.util.Map;

/** AFTER: stands in for RealInventoryService, caching results so repeat lookups are free. */
public class CachingInventoryProxy implements InventoryLookup {
    private final RealInventoryService realService;
    private final Map<String, Integer> cache = new HashMap<>();

    public CachingInventoryProxy(RealInventoryService realService) {
        this.realService = realService;
    }

    @Override
    public int getStock(String sku) {
        return cache.computeIfAbsent(sku, realService::getStock);
    }
}
