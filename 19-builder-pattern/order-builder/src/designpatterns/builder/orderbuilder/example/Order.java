package designpatterns.builder.orderbuilder.example;

import java.util.List;

/** AFTER: construction goes through the nested Builder — no constructor overloads to maintain. */
public class Order {
    private final String customerEmail;
    private final List<String> items;
    private final String giftMessage;
    private final boolean giftWrapped;

    private Order(Builder builder) {
        this.customerEmail = builder.customerEmail;
        this.items = builder.items;
        this.giftMessage = builder.giftMessage;
        this.giftWrapped = builder.giftWrapped;
    }

    @Override
    public String toString() {
        return customerEmail + " " + items + " giftMessage=" + giftMessage + " giftWrapped=" + giftWrapped;
    }

    public static class Builder {
        private final String customerEmail;
        private final List<String> items;
        private String giftMessage;
        private boolean giftWrapped;

        public Builder(String customerEmail, List<String> items) {
            this.customerEmail = customerEmail;
            this.items = items;
        }

        public Builder giftMessage(String giftMessage) {
            this.giftMessage = giftMessage;
            return this;
        }

        public Builder giftWrapped(boolean giftWrapped) {
            this.giftWrapped = giftWrapped;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
