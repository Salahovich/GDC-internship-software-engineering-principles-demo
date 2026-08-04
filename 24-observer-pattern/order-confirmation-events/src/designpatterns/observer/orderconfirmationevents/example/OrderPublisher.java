package designpatterns.observer.orderconfirmationevents.example;

import java.util.ArrayList;
import java.util.List;

/** AFTER: the subject. Notifies whoever registered, without knowing what they do. */
public class OrderPublisher {
    private final List<OrderListener> listeners = new ArrayList<>();

    public void addListener(OrderListener listener) {
        listeners.add(listener);
    }

    public void confirm(Order order) {
        for (OrderListener listener : listeners) {
            listener.onOrderConfirmed(order);
        }
    }
}
