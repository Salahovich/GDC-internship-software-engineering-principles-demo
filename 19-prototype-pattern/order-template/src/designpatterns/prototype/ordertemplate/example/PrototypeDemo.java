package designpatterns.prototype.ordertemplate.example;

import java.util.ArrayList;
import java.util.List;

/**
 * PROTOTYPE PATTERN
 * ----------------------------------------------------------------------
 * Lets an object create a correct copy of itself (via a copy()/clone()
 * method) instead of every caller re-implementing "new the same fields"
 * by hand and risking a shallow copy or a missed field.
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: OrderManualCopy — a hand-written copy that shares the list ==");
        List<String> items = new ArrayList<>(List.of("Mug"));
        OrderManualCopy original = new OrderManualCopy("amina@example.com", items);
        OrderManualCopy copy = original.copyForgettingItems();
        copy.getItems().add("Extra item only meant for the copy");
        System.out.println("original items: " + original.getItems());
        System.out.println("copy items:     " + copy.getItems());
        System.out.println("^ Bug: both lists changed. copy() returned the SAME List reference.");

        System.out.println();
        System.out.println("== AFTER: Order.copy() — a correct, independent copy ==");
        Order originalOrder = new Order("amina@example.com", new ArrayList<>(List.of("Mug")));
        Order orderCopy = originalOrder.copy();
        orderCopy.getItems().add("Extra item only meant for the copy");
        System.out.println("original items: " + originalOrder.getItems());
        System.out.println("copy items:     " + orderCopy.getItems());
        System.out.println("^ Fixed: copy() made a new list, so editing the copy leaves the original alone.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/QuoteCopyTodo.java ==");
    }
}
