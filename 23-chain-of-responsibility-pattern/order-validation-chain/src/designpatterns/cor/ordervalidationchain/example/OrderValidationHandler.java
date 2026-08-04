package designpatterns.cor.ordervalidationchain.example;

/** AFTER: base class. Each handler does one check, then passes the order along. */
public abstract class OrderValidationHandler {
    private OrderValidationHandler next;

    public OrderValidationHandler setNext(OrderValidationHandler next) {
        this.next = next;
        return next;
    }

    public final boolean handle(Order order) {
        if (!check(order)) {
            return false;
        }
        if (next != null) {
            return next.handle(order);
        }
        return true;
    }

    protected abstract boolean check(Order order);
}
