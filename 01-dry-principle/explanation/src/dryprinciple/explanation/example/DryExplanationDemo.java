package dryprinciple.explanation.example;

import dryprinciple.explanation.before.DuplicatedPricing;

import dryprinciple.explanation.exercise.DuplicatedReceiptPrinters;

/**
 * DRY PRINCIPLE — PART 1: EXPLANATION
 * ----------------------------------------------------------------------
 * "Don't Repeat Yourself": every piece of knowledge (a business rule, a
 * calculation, a format) should have a single, unambiguous representation
 * in the codebase. When the SAME rule is copy-pasted in multiple places,
 * a change to that rule means hunting down every copy — and it's easy to
 * miss one, leaving the system inconsistent.
 */
public class DryExplanationDemo {

    public static void main(String[] args) {
        Product book = new Product("Clean Code", "BOOK", 40);
        Product laptop = new Product("ThinkPad", "ELECTRONICS", 900);
        Product shirt = new Product("T-Shirt", "CLOTHING", 25);

        System.out.println("== BEFORE: duplicated logic across 3 near-identical methods ==");
        System.out.println("Book total:        $" + DuplicatedPricing.bookTotal(book, 2));
        System.out.println("Electronics total: $" + DuplicatedPricing.electronicsTotal(laptop, 1));
        System.out.println("Clothing total:    $" + DuplicatedPricing.clothingTotal(shirt, 5));

        System.out.println();
        System.out.println("== AFTER: one method, single source of truth ==");
        System.out.println("Book total:        $" + UnifiedPricing.calculateTotal(book, 2));
        System.out.println("Electronics total: $" + UnifiedPricing.calculateTotal(laptop, 1));
        System.out.println("Clothing total:    $" + UnifiedPricing.calculateTotal(shirt, 5));
        System.out.println("(Same results — the tax rule now lives in exactly one place: taxRateFor())");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/DuplicatedReceiptPrinters.java ==");
        DuplicatedReceiptPrinters.printInStoreReceipt(book, 2);
        DuplicatedReceiptPrinters.printOnlineReceipt(book, 2);
    }
}
