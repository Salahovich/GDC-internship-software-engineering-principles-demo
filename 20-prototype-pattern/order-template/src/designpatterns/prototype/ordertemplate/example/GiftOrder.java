package designpatterns.prototype.ordertemplate.example;

import java.util.List;
import java.util.Objects;

/**
 * A second, unrelated order template with its own extra field
 * (giftMessage). PrototypeDemo.cloneAll() didn't need a single line
 * changed to support this new type — that's the point of the pattern.
 */
public class GiftOrder extends Order {
    private String giftMessage;

    public GiftOrder(String customerEmail, List<String> items, String giftMessage) {
        this.customerEmail = customerEmail;
        this.items = items;
        this.giftMessage = giftMessage;
    }

    protected GiftOrder(GiftOrder target) {
        super(target);
        if (target != null) {
            this.giftMessage = target.giftMessage;
        }
    }

    @Override
    public GiftOrder copy() {
        return new GiftOrder(this);
    }

    public String getGiftMessage() {
        return giftMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // super.equals() already checked getClass() == GiftOrder.class, so this cast is safe.
        if (!super.equals(o)) return false;
        return Objects.equals(giftMessage, ((GiftOrder) o).giftMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), giftMessage);
    }
}
