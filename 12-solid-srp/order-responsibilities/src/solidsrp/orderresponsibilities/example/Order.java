package solidsrp.orderresponsibilities.example;

import java.util.List;

/** Shared entity — reused (redeclared) across all five SOLID demos. */
public class Order {
    private final String customerEmail;
    private final List<OrderItem> items;

    public Order(String customerEmail, List<OrderItem> items) {
        this.customerEmail = customerEmail;
        this.items = items;
    }

    public String getCustomerEmail() { return customerEmail; }
    public List<OrderItem> getItems() { return items; }

    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
    }
}
