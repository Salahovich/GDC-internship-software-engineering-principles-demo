package cohesionandcoupling.storeoperations;

import java.util.HashMap;
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
 *
 * This demo bundles inventory, payroll, and promotional email into one
 * class (BEFORE) — low cohesion, and every caller pays for it with
 * unnecessary coupling — then splits it into three focused classes
 * (AFTER) — high cohesion, and callers depend on only what they need.
 */
public class CohesionAndCouplingDemo {

    // ======================================================================
    // 1) BEFORE — low cohesion, high coupling.
    //    StoreManagerLowCohesion does three UNRELATED jobs: inventory,
    //    payroll, and promotional email. Nothing about "restocking a
    //    shelf" has anything to do with "calculating a paycheck" or
    //    "emailing a coupon" — they're only together because they all
    //    happen to relate to "running a store."
    //
    //    The cost shows up in its constructor: ANY caller that wants
    //    this class — even one that only ever calls restockItem() — is
    //    forced to depend on (and construct/wire up) a pay rate table
    //    and an email sender address too. That's coupling that has
    //    nothing to do with what the caller actually needed.
    // ======================================================================

    static class StoreManagerLowCohesion {
        private final Map<String, Integer> stock = new HashMap<>();
        private final Map<String, Double> hourlyPayRates; // payroll concern
        private final String emailSenderAddress;           // email concern

        StoreManagerLowCohesion(Map<String, Double> hourlyPayRates, String emailSenderAddress) {
            this.hourlyPayRates = hourlyPayRates;
            this.emailSenderAddress = emailSenderAddress;
        }

        // -- inventory concern --
        void restockItem(String item, int qty) {
            stock.merge(item, qty, Integer::sum);
        }

        int getStock(String item) {
            return stock.getOrDefault(item, 0);
        }

        // -- payroll concern --
        double calculatePay(String employeeRole, double hoursWorked) {
            double rate = hourlyPayRates.getOrDefault(employeeRole, 0.0);
            return rate * hoursWorked;
        }

        // -- promotional email concern --
        void sendPromotionEmail(String customerEmail, String promoCode) {
            System.out.println("[" + emailSenderAddress + " -> " + customerEmail + "] Use code " + promoCode + " for 10% off!");
        }
    }

    // ======================================================================
    // 2) AFTER — high cohesion, low coupling.
    //    Three small classes, each with ONE reason to change. A caller
    //    that only wants inventory depends on InventoryManager alone —
    //    it never even hears about pay rates or email addresses.
    // ======================================================================

    static class InventoryManager {
        private final Map<String, Integer> stock = new HashMap<>();

        void restockItem(String item, int qty) {
            stock.merge(item, qty, Integer::sum);
        }

        int getStock(String item) {
            return stock.getOrDefault(item, 0);
        }
    }

    static class PayrollCalculator {
        private final Map<String, Double> hourlyPayRates;

        PayrollCalculator(Map<String, Double> hourlyPayRates) {
            this.hourlyPayRates = hourlyPayRates;
        }

        double calculatePay(String employeeRole, double hoursWorked) {
            double rate = hourlyPayRates.getOrDefault(employeeRole, 0.0);
            return rate * hoursWorked;
        }
    }

    static class PromotionNotifier {
        private final String emailSenderAddress;

        PromotionNotifier(String emailSenderAddress) {
            this.emailSenderAddress = emailSenderAddress;
        }

        void sendPromotionEmail(String customerEmail, String promoCode) {
            System.out.println("[" + emailSenderAddress + " -> " + customerEmail + "] Use code " + promoCode + " for 10% off!");
        }
    }

    // A caller that only cares about stock now depends on ONE small,
    // focused class — no pay rates, no email sender, nothing unrelated.
    static void printStockReport(InventoryManager inventory, String item) {
        System.out.println(item + " in stock: " + inventory.getStock(item));
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    ReceiptPrinterLowCohesion below bundles TWO unrelated jobs:
    //    formatting a receipt line and tracking a running loyalty-points
    //    balance. A caller that only wants to format text is still
    //    forced to depend on (and construct) the loyalty points store.
    //
    //    Your task:
    //      a) Split it into ReceiptFormatter (formatLine(itemName,
    //         price)) and LoyaltyPointsTracker (addPoints(customerId,
    //         points), getPoints(customerId)).
    //      b) Update main() to use the two new classes directly instead
    //         of ReceiptPrinterLowCohesion.
    //      c) Delete ReceiptPrinterLowCohesion once nothing uses it.
    //    Goal: notice how splitting the unrelated responsibility apart
    //    means a "just print a receipt line" caller no longer needs to
    //    know loyalty points exist at all — cohesion went up, coupling
    //    went down.
    // ======================================================================

    static class ReceiptPrinterLowCohesion {
        private final Map<String, Integer> loyaltyPoints = new HashMap<>();

        String formatLine(String itemName, double price) {
            return " - " + itemName + ": $" + price;
        }

        void addPoints(String customerId, int points) {
            loyaltyPoints.merge(customerId, points, Integer::sum);
        }

        int getPoints(String customerId) {
            return loyaltyPoints.getOrDefault(customerId, 0);
        }
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
        System.out.println("== TODO exercise: split ReceiptPrinterLowCohesion below ==");
        ReceiptPrinterLowCohesion receiptPrinter = new ReceiptPrinterLowCohesion();
        System.out.println(receiptPrinter.formatLine("Keyboard", 60.0));
        receiptPrinter.addPoints("amina", 6);
        System.out.println("Amina's points: " + receiptPrinter.getPoints("amina"));
    }
}
