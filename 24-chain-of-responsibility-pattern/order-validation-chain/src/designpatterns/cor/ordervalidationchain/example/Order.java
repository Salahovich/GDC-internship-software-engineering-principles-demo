package designpatterns.cor.ordervalidationchain.example;

import java.util.List;

/** Shared entity. */
public class Order {
    private final String customerEmail;
    private final List<String> items;
    private final boolean inStock;

    public Order(String customerEmail, List<String> items, boolean inStock) {
        this.customerEmail = customerEmail;
        this.items = items;
        this.inStock = inStock;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<String> getItems() {
        return items;
    }

    public boolean isInStock() {
        return inStock;
    }
}
