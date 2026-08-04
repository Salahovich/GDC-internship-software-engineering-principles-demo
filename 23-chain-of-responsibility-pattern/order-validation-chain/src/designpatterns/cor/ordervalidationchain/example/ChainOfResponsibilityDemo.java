package designpatterns.cor.ordervalidationchain.example;

import java.util.List;

/**
 * CHAIN OF RESPONSIBILITY PATTERN
 * ----------------------------------------------------------------------
 * Passes a request along a chain of independent handlers, each deciding
 * whether to handle it (and stop the chain) or pass it to the next one.
 * Adding a new check means writing a new handler, not editing existing
 * ones.
 */
public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        Order goodOrder = new Order("amina@example.com", List.of("Mug"), true);
        Order badOrder = new Order("not-an-email", List.of("Mug"), true);

        System.out.println("== BEFORE: OrderValidatorMonolithic — one method, every check inline ==");
        new OrderValidatorMonolithic().validate(goodOrder);
        new OrderValidatorMonolithic().validate(badOrder);
        System.out.println("^ Adding a fraud check means editing this method and re-testing all of it.");

        System.out.println();
        System.out.println("== AFTER: a chain of independent handlers ==");
        OrderValidationHandler chain = new EmailValidationHandler();
        chain.setNext(new StockAvailabilityHandler());

        System.out.println("Good order valid? " + chain.handle(goodOrder));
        System.out.println("Bad order valid?  " + chain.handle(badOrder));
        System.out.println("^ Each handler only knows its own check and how to reach the next one.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/FraudCheckHandlerTodo.java ==");
    }
}
