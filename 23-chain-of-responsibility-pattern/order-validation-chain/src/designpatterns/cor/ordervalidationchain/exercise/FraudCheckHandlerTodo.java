package designpatterns.cor.ordervalidationchain.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Risk wants a third check added to the chain: reject any order with
 * more than 10 items (a crude fraud signal for this exercise).
 *
 * Task: write `FraudCheckHandler extends OrderValidationHandler` (in its
 * own file, next to this one) whose check(order) rejects (returns false,
 * after printing a rejection message) when order.getItems().size() > 10,
 * and otherwise returns true — the same shape as
 * `StockAvailabilityHandler` in the example package.
 *
 * Then, in a small main(), build a chain of all three handlers
 * (email -> stock -> fraud) using setNext(), and confirm an order with
 * 11 items gets rejected by the new handler.
 */
public class FraudCheckHandlerTodo {
}
