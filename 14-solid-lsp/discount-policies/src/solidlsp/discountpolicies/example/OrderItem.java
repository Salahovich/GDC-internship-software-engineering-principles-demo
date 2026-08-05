package solidlsp.discountpolicies.example;

/** Shared entity — same as the earlier SOLID demos. */
public class OrderItem {
    private final String name;
    private final double price;
    private final int qty;

    public OrderItem(String name, double price, int qty) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public double getLineTotal() { return price * qty; }
}
