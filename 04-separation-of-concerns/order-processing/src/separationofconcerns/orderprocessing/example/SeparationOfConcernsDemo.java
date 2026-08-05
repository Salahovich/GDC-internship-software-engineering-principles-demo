package separationofconcerns.orderprocessing.example;

import separationofconcerns.orderprocessing.before.OrderProcessingTangled;

import separationofconcerns.orderprocessing.exercise.RegisterUserTangled;

import java.util.List;

/**
 * SEPARATION OF CONCERNS (SoC)
 * ----------------------------------------------------------------------
 * Each part of a program should be responsible for ONE concern —
 * validation, calculation, formatting, sending, etc. Mixing them into a
 * single method or class makes every concern harder to test, reuse, or
 * change without accidentally breaking the others.
 */
public class SeparationOfConcernsDemo {

    public static void main(String[] args) {
        List<Item> items = List.of(new Item("Mouse", 25.0, 2), new Item("Keyboard", 60.0, 1));

        System.out.println("== BEFORE: validation + math + formatting + sending, all tangled ==");
        OrderProcessingTangled.processOrderTangled("amina@example.com", items);

        System.out.println();
        System.out.println("== AFTER: four focused pieces, orchestrated by OrderProcessor ==");
        OrderProcessor.processOrder("amina@example.com", items);

        System.out.println();
        System.out.println("== TODO exercise: see exercise/RegisterUserTangled.java ==");
        RegisterUserTangled.registerUserTangled("amina", "s3cur3pw!");
    }
}
