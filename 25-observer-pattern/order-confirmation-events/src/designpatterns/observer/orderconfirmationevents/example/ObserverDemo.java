package designpatterns.observer.orderconfirmationevents.example;

/**
 * OBSERVER PATTERN
 * ----------------------------------------------------------------------
 * Defines a one-to-many dependency: when the subject changes state, all
 * registered listeners are notified automatically, without the subject
 * knowing what any of them do.
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Order order = new Order("amina@example.com", 110.0);

        System.out.println("== BEFORE: OrderNotifierHardcoded knows every interested party by name ==");
        new OrderNotifierHardcoded().confirm(order);
        System.out.println("^ Adding a new interested party means editing this class.");

        System.out.println();
        System.out.println("== AFTER: OrderPublisher just notifies whoever registered ==");
        OrderPublisher publisher = new OrderPublisher();
        publisher.addListener(new InventoryUpdater());
        publisher.addListener(new EmailSender());
        publisher.confirm(order);
        System.out.println("^ Same reactions, but OrderPublisher never named a single one of them.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/LoyaltyPointsListenerTodo.java ==");
    }
}
