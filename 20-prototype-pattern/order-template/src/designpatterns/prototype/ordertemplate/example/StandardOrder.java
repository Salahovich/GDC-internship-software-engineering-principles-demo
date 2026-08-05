package designpatterns.prototype.ordertemplate.example;

import java.util.List;

/** A plain order template — no fields beyond the Order base. */
public class StandardOrder extends Order {

    public StandardOrder(String customerEmail, List<String> items) {
        this.customerEmail = customerEmail;
        this.items = items;
    }

    /** Copy constructor: chains to Order's, which already deep-copies customerEmail and items. */
    protected StandardOrder(StandardOrder target) {
        super(target);
    }

    @Override
    public StandardOrder copy() {
        return new StandardOrder(this);
    }
}
