package solidlsp.discountpolicies.example;

import java.util.List;

/** Shared entity — same as the earlier SOLID demos. */
public class Order {
    private final String customerEmail;
    private final List<OrderItem> items;

    public Order(String customerEmail, List<OrderItem> items) {
        this.customerEmail = customerEmail;
        this.items = items;
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
    }
}
