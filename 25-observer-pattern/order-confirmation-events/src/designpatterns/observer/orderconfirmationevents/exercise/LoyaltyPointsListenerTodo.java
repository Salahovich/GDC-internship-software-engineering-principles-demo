package designpatterns.observer.orderconfirmationevents.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Marketing wants loyalty points awarded automatically on every
 * confirmed order.
 *
 * Task: write `LoyaltyPointsListener implements OrderListener` (in its
 * own file, next to this one) whose onOrderConfirmed(order) prints
 * something like "[Loyalty] +N points for <email>", where N is
 * order.getTotal() / 10 (rounded down).
 *
 * Then register an instance of it with an OrderPublisher in a small
 * main(), alongside InventoryUpdater and EmailSender, and confirm all
 * three reactions run. You should not need to touch OrderPublisher or
 * either existing listener to do this.
 */
public class LoyaltyPointsListenerTodo {
}
