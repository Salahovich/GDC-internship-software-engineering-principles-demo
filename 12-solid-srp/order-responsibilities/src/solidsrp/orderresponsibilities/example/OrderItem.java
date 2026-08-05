package solidsrp.orderresponsibilities.example;

/** Shared entity — reused (redeclared) across all five SOLID demos. */
public class OrderItem {
    private final String name;
    private final double price;
    private final int qty;

    public OrderItem(String name, double price, int qty) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getName() { return name; }
    public double getLineTotal() { return price * qty; }
}
