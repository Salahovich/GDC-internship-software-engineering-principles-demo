package designpatterns.builder.orderbuilder.example;

import java.util.List;

/** BEFORE: one constructor per combination of optional fields — the "telescoping" problem. */
public class TelescopingOrder {
    private final String customerEmail;
    private final List<String> items;
    private final String giftMessage;
    private final boolean giftWrapped;

    public TelescopingOrder(String customerEmail, List<String> items) {
        this(customerEmail, items, null, false);
    }

    public TelescopingOrder(String customerEmail, List<String> items, String giftMessage) {
        this(customerEmail, items, giftMessage, false);
    }

    public TelescopingOrder(String customerEmail, List<String> items, String giftMessage, boolean giftWrapped) {
        this.customerEmail = customerEmail;
        this.items = items;
        this.giftMessage = giftMessage;
        this.giftWrapped = giftWrapped;
    }

    @Override
    public String toString() {
        return customerEmail + " " + items + " giftMessage=" + giftMessage + " giftWrapped=" + giftWrapped;
    }
}
