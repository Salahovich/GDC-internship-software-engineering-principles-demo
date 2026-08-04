package designpatterns.adapter.paymentadapter.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * New checkout code is written against this interface:
 *
 *   public interface InventoryLookup {
 *       int getStock(String sku);
 *   }
 *
 * But stock actually lives in LegacyInventorySystem (see
 * LegacyInventorySystem.java in this package), whose method is called
 * `checkQuantityOnHand`, not `getStock`.
 *
 * Task:
 *   1. Write the `InventoryLookup` interface (in its own file).
 *   2. Write `LegacyInventorySystemAdapter implements InventoryLookup`
 *      that wraps a `LegacyInventorySystem` and implements `getStock(sku)`
 *      by delegating to `checkQuantityOnHand(sku)` — the same shape as
 *      `LegacyPaymentGatewayAdapter` in the example package.
 *
 * Then, in a small main(), call getStock("MUG-01") through the adapter
 * and confirm it returns 42.
 */
public class InventoryLookupAdapterTodo {
}
