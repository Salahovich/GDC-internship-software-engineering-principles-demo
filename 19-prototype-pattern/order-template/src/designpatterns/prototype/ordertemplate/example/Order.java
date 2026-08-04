package designpatterns.prototype.ordertemplate.example;

import java.util.ArrayList;
import java.util.List;

/** AFTER: the object knows how to copy itself correctly, including a deep copy of its list. */
public class Order {
    private String customerEmail;
    private List<String> items;

    public Order(String customerEmail, List<String> items) {
        this.customerEmail = customerEmail;
        this.items = items;
    }

    /** Prototype method: returns a correct, independent copy. */
    public Order copy() {
        return new Order(this.customerEmail, new ArrayList<>(this.items));
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<String> getItems() {
        return items;
    }
}
