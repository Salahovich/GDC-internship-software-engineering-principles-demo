package designpatterns.observer.orderconfirmationevents.example;

/** AFTER: the abstraction reactions implement. */
public interface OrderListener {
    void onOrderConfirmed(Order order);
}
