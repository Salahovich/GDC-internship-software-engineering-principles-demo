package designpatterns.observer.orderconfirmationevents.example;

/** Shared entity. */
public class Order {
    private final String customerEmail;
    private final double total;

    public Order(String customerEmail, double total) {
        this.customerEmail = customerEmail;
        this.total = total;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public double getTotal() {
        return total;
    }
}
