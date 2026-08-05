package cohesionandcoupling.storeoperations.example;

import cohesionandcoupling.storeoperations.exercise.ReceiptPrinterLowCohesion;

import java.util.Map;

/**
 * COHESION & COUPLING
 * ----------------------------------------------------------------------
 * Cohesion: how closely related the responsibilities INSIDE a class are.
 * A highly cohesive class does one thing, and everything in it serves
 * that one thing.
 *
 * Coupling: how much one part of the system depends on the internals
 * (or even just the existence) of another part. Low coupling means a
 * change in one place is unlikely to ripple into unrelated code.
 *
 * The two are linked: a class with LOW cohesion (it does several
 * unrelated things) tends to force HIGH coupling onto everything that
 * uses it — callers who only care about ONE of its responsibilities
 * still end up depending on all the others, because they can't be
 * separated. Raising cohesion (splitting responsibilities apart) is
 * usually exactly what LOWERS coupling elsewhere in the system.
 */
public class CohesionAndCouplingDemo {

    // A caller that only cares about stock now depends on ONE small,
    // focused class — no pay rates, no email sender, nothing unrelated.
    static void printStockReport(InventoryManager inventory, String item) {
        System.out.println(item + " in stock: " + inventory.getStock(item));
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: one low-cohesion class, unrelated jobs bundled together ==");
        StoreManagerLowCohesion manager = new StoreManagerLowCohesion(
                Map.of("cashier", 18.50), "promos@store.example"); // even an inventory-only caller must supply these
        manager.restockItem("Mouse", 20);
        System.out.println("Mouse stock: " + manager.getStock("Mouse"));
        System.out.println("Cashier pay for 6h: $" + manager.calculatePay("cashier", 6));
        manager.sendPromotionEmail("amina@example.com", "SAVE10");
        System.out.println("^ One class knows about shelves, paychecks, AND email — three unrelated reasons to change it.");

        System.out.println();
        System.out.println("== AFTER: three focused, highly cohesive classes ==");
        InventoryManager inventory = new InventoryManager(); // no pay rates, no email needed
        inventory.restockItem("Mouse", 20);
        printStockReport(inventory, "Mouse");

        PayrollCalculator payroll = new PayrollCalculator(Map.of("cashier", 18.50));
        System.out.println("Cashier pay for 6h: $" + payroll.calculatePay("cashier", 6));

        PromotionNotifier notifier = new PromotionNotifier("promos@store.example");
        notifier.sendPromotionEmail("amina@example.com", "SAVE10");
        System.out.println("^ Each class has exactly one reason to change, and printStockReport() above");
        System.out.println("  depends on InventoryManager alone — it was never coupled to payroll or email.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/ReceiptPrinterLowCohesion.java ==");
        ReceiptPrinterLowCohesion receiptPrinter = new ReceiptPrinterLowCohesion();
        System.out.println(receiptPrinter.formatLine("Keyboard", 60.0));
        receiptPrinter.addPoints("amina", 6);
        System.out.println("Amina's points: " + receiptPrinter.getPoints("amina"));
    }
}
