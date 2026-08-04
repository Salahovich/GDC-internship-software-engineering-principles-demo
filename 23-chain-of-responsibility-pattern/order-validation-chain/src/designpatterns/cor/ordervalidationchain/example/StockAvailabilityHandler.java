package designpatterns.cor.ordervalidationchain.example;

public class StockAvailabilityHandler extends OrderValidationHandler {
    @Override
    protected boolean check(Order order) {
        if (!order.isInStock()) {
            System.out.println("[StockAvailabilityHandler] Rejected: out of stock");
            return false;
        }
        return true;
    }
}
