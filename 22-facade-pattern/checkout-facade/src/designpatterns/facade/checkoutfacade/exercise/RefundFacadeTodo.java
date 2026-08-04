package designpatterns.facade.checkoutfacade.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Support wants to issue refunds, which also touches three things: give
 * the money back, put the stock back, and let the customer know.
 *
 * Task: add a `refund(String customerEmail, String sku, int quantity, double amount)`
 * method to `CheckoutFacade` (in the example package) that, in order:
 *   1. calls a new `PaymentService.refund(customerEmail, amount)` (add
 *      this method to PaymentService — just print a line like
 *      placeOrder's methods do)
 *   2. calls `InventoryService.reserve(sku, -quantity)` to put the stock
 *      back (reusing reserve with a negative quantity is fine here)
 *   3. calls `NotificationService.sendOrderConfirmation` with a refund
 *      message instead (or add a small `sendRefundConfirmation` method)
 *
 * Then call `checkoutFacade.refund(...)` from a small main() and confirm
 * all three steps run in order, exactly like `placeOrder` does.
 */
public class RefundFacadeTodo {
}
