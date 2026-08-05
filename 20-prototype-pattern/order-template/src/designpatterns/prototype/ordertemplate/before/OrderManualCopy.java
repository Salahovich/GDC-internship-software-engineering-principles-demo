package designpatterns.prototype.ordertemplate.before;

import java.util.List;

/** BEFORE: copying by hand, re-declaring the constructor call. Easy to forget a field. */
public class OrderManualCopy {
    private String customerEmail;
    private List<String> items;

    public OrderManualCopy(String customerEmail, List<String> items) {
        this.customerEmail = customerEmail;
        this.items = items;
    }

    /** "Copy" that a developer wrote by hand — forgot to copy the items list. */
    public OrderManualCopy copyForgettingItems() {
        return new OrderManualCopy(this.customerEmail, this.items);
        // looks right, but see the demo: the caller then mutates `items`
        // and BOTH orders change, because it's the same List reference.
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<String> getItems() {
        return items;
    }
}
