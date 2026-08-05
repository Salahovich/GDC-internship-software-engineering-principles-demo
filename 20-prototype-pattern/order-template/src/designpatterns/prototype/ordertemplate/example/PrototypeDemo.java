package designpatterns.prototype.ordertemplate.example;

import designpatterns.prototype.ordertemplate.before.OrderManualCopy;

import java.util.ArrayList;
import java.util.List;

/**
 * PROTOTYPE PATTERN
 * ----------------------------------------------------------------------
 * Lets an object create a correct copy of itself instead of every caller
 * re-implementing "new the same fields" by hand. The actual point (see
 * cloneAll() below, and Order/StandardOrder/GiftOrder): a client can copy
 * a whole collection of DIFFERENT concrete order types through one shared
 * Order reference, with no "if it's this type do X, if it's that type do
 * Y" branch — and a brand-new order type never requires touching the
 * cloning code, only the new subclass itself.
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
        System.out.println("^ Bug: both lists changed. copyForgettingItems() returned the SAME List reference.");

        System.out.println();
        System.out.println("== AFTER: cloning a catalog of DIFFERENT order templates through one abstract type ==");
        List<Order> templates = new ArrayList<>();
        templates.add(new StandardOrder("amina@example.com", new ArrayList<>(List.of("Mug"))));
        templates.add(new GiftOrder("noah@example.com", new ArrayList<>(List.of("Candle")), "Happy Birthday!"));

        List<Order> reorders = cloneAll(templates); // never mentions StandardOrder or GiftOrder by name

        for (int i = 0; i < templates.size(); i++) {
            Order template = templates.get(i);
            Order reorder = reorders.get(i);
            System.out.println(template.getClass().getSimpleName()
                + " -> same object? " + (template == reorder)
                + " | equal values? " + template.equals(reorder));
        }
        System.out.println("^ cloneAll() never checked \"instanceof StandardOrder / instanceof GiftOrder\" — copy() is polymorphic.");

        System.out.println();
        System.out.println("== Independence check: editing a reorder must not touch its template ==");
        reorders.get(0).getItems().add("Extra item only meant for the reorder");
        System.out.println("template[0] items: " + templates.get(0).getItems());
        System.out.println("reorder[0] items:  " + reorders.get(0).getItems());
        System.out.println("^ Fixed: the copy constructor built a NEW items list, so they don't share state.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/QuoteCopyTodo.java ==");
    }

    /** Client code: only ever touches Order. It has never heard of StandardOrder or GiftOrder. */
    private static List<Order> cloneAll(List<Order> templates) {
        List<Order> clones = new ArrayList<>();
        for (Order template : templates) {
            clones.add(template.copy());
        }
        return clones;
    }
}
