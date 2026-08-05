package dryprinciple.explanation.exercise;

import dryprinciple.explanation.example.Product;
import dryprinciple.explanation.example.UnifiedPricing;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * These two receipt printers both repeat the SAME currency-formatting
 * rule and the SAME loyalty-points rule.
 *
 * Task:
 *   a) Write one shared method, e.g.
 *        static void printReceipt(String channel, Product p, int qty)
 *      that computes the total, formats it, computes points, and prints
 *      the line — taking "In-Store" or "Online" as the label.
 *   b) Replace the two methods below (and their calls in
 *      DryExplanationDemo.main) with calls to your new shared method.
 *
 * Goal: end up with ONE place that knows "how a receipt line looks",
 * just like UnifiedPricing.calculateTotal() is the ONE place that knows
 * the pricing rule.
 */
public class DuplicatedReceiptPrinters {

    public static void printInStoreReceipt(Product p, int qty) {
        double total = UnifiedPricing.calculateTotal(p, qty);
        String formatted = String.format("$%.2f", total); // duplicated
        int points = (int) (total / 10); // duplicated
        System.out.println("[In-Store] " + p.name() + " x" + qty + " = " + formatted + " (" + points + " pts)");
    }

    public static void printOnlineReceipt(Product p, int qty) {
        double total = UnifiedPricing.calculateTotal(p, qty);
        String formatted = String.format("$%.2f", total); // duplicated
        int points = (int) (total / 10); // duplicated
        System.out.println("[Online]   " + p.name() + " x" + qty + " = " + formatted + " (" + points + " pts)");
    }
}
