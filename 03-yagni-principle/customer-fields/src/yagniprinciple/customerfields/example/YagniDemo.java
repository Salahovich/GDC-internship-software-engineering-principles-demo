package yagniprinciple.customerfields.example;

import yagniprinciple.customerfields.before.CustomerOverEngineered;

import yagniprinciple.customerfields.exercise.OrderOverEngineered;

/**
 * YAGNI PRINCIPLE ("You Aren't Gonna Need It")
 * ----------------------------------------------------------------------
 * Don't add functionality — fields, parameters, config, abstraction —
 * until a real requirement needs it. "We might need this later"
 * speculation adds maintenance cost (more fields to fill in, more null
 * checks, bigger constructors) today, for a feature that may never ship,
 * designed against a guess instead of a real spec.
 */
public class YagniDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: Customer has 5 speculative fields, only 2 ever used ==");
        CustomerOverEngineered bloated = new CustomerOverEngineered(
                "Amina", "amina@example.com",
                null, null, null, null, null); // 5 of 7 args are "we don't know yet"
        System.out.println(CustomerOverEngineered.buildConfirmationEmail(bloated, "ORD-1001"));

        System.out.println();
        System.out.println("== AFTER: Customer has exactly what today's feature needs ==");
        Customer trimmed = new Customer("Amina", "amina@example.com");
        System.out.println(Customer.buildConfirmationEmail(trimmed, "ORD-1001"));

        System.out.println();
        System.out.println("== TODO exercise: see exercise/OrderOverEngineered.java ==");
        OrderOverEngineered order = new OrderOverEngineered(
                java.util.List.of(19.99, 34.50), 10.0,
                0.0, false, java.util.List.of()); // 3 of 5 fields are unused padding
        System.out.println("Total: $" + order.calculateTotal());
    }
}
