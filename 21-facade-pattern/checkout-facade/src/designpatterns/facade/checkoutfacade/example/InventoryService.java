package designpatterns.facade.checkoutfacade.example;

/** One of three subsystems checkout has to coordinate. */
public class InventoryService {
    public boolean hasStock(String sku, int quantity) {
        System.out.println("[InventoryService] Checking stock for " + sku + " x" + quantity);
        return true;
    }

    public void reserve(String sku, int quantity) {
        System.out.println("[InventoryService] Reserved " + sku + " x" + quantity);
    }
}
