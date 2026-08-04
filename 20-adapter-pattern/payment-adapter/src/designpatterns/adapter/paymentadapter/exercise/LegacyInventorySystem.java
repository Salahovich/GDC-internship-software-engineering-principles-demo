package designpatterns.adapter.paymentadapter.exercise;

/** Given: a legacy inventory system with an incompatible method name/shape. */
public class LegacyInventorySystem {
    public int checkQuantityOnHand(String sku) {
        // pretend this looks the SKU up in an old database
        return sku.equals("MUG-01") ? 42 : 0;
    }
}
