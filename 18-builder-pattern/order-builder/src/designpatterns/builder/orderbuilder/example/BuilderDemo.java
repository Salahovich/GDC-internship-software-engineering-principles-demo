package designpatterns.builder.orderbuilder.example;

import java.util.List;

/**
 * BUILDER PATTERN
 * ----------------------------------------------------------------------
 * Separates constructing a complex object from representing it, so you
 * don't need a constructor overload for every combination of optional
 * fields. Named, chainable setter methods make the call site read like
 * a sentence instead of a wall of positional arguments.
 */
public class BuilderDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: TelescopingOrder — which constructor has giftWrapped again? ==");
        TelescopingOrder plain = new TelescopingOrder("amina@example.com", List.of("Mug"));
        TelescopingOrder gift = new TelescopingOrder("amina@example.com", List.of("Mug"), "Happy Birthday!", true);
        System.out.println(plain);
        System.out.println(gift);
        System.out.println("^ Callers have to know which overload takes which flags, in which order.");

        System.out.println();
        System.out.println("== AFTER: Order.Builder — named methods, read top to bottom ==");
        Order order = new Order.Builder("amina@example.com", List.of("Mug"))
                .giftMessage("Happy Birthday!")
                .giftWrapped(true)
                .build();
        System.out.println(order);

        System.out.println();
        System.out.println("== TODO exercise: see exercise/ShippingLabelBuilderTodo.java ==");
    }
}
