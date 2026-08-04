package separationofconcerns.orderprocessing;

import java.util.List;

/**
 * SEPARATION OF CONCERNS (SoC)
 * ----------------------------------------------------------------------
 * Each part of a program should be responsible for ONE concern —
 * validation, calculation, formatting, sending, etc. Mixing them into a
 * single method or class makes every concern harder to test, reuse, or
 * change without accidentally breaking the others.
 *
 * This demo processes an order two ways: BEFORE, one method that
 * validates, calculates, formats, and "sends" all tangled together;
 * AFTER, four small pieces that each own exactly one concern.
 */
public class SeparationOfConcernsDemo {

    record Item(String name, double price, int qty) {}

    // ======================================================================
    // 1) BEFORE — violates SoC.
    //    One method does validation, math, string formatting, AND
    //    "sending" (here, printing) all tangled together. Want to change
    //    the receipt wording? You're editing the same method that also
    //    validates input and computes totals — easy to break one while
    //    touching the other.
    // ======================================================================

    static void processOrderTangled(String customerEmail, List<Item> items) {
        // -- validation concern --
        if (customerEmail == null || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("invalid email: " + customerEmail);
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("order must contain at least one item");
        }

        // -- calculation concern --
        double total = 0;
        for (Item item : items) {
            total += item.price() * item.qty();
        }

        // -- formatting concern --
        StringBuilder receipt = new StringBuilder("Receipt for " + customerEmail + ":\n");
        for (Item item : items) {
            receipt.append(" - ").append(item.name()).append(" x").append(item.qty())
                    .append(" = $").append(item.price() * item.qty()).append("\n");
        }
        receipt.append("Total: $").append(total);

        // -- "sending" concern --
        System.out.println(receipt);
        System.out.println("[sent to " + customerEmail + "]");
    }

    // ======================================================================
    // 2) AFTER — SoC applied.
    //    Each concern is its own small piece. processOrder() just
    //    orchestrates them in order. Change the receipt wording and you
    //    touch only ReceiptFormatter; change the tax rule and you touch
    //    only PriceCalculator — nothing else even needs to be recompiled
    //    against different behavior.
    // ======================================================================

    static class OrderValidator {
        static void validate(String customerEmail, List<Item> items) {
            if (customerEmail == null || !customerEmail.contains("@")) {
                throw new IllegalArgumentException("invalid email: " + customerEmail);
            }
            if (items == null || items.isEmpty()) {
                throw new IllegalArgumentException("order must contain at least one item");
            }
        }
    }

    static class PriceCalculator {
        static double calculateTotal(List<Item> items) {
            double total = 0;
            for (Item item : items) {
                total += item.price() * item.qty();
            }
            return total;
        }
    }

    static class ReceiptFormatter {
        static String format(String customerEmail, List<Item> items, double total) {
            StringBuilder receipt = new StringBuilder("Receipt for " + customerEmail + ":\n");
            for (Item item : items) {
                receipt.append(" - ").append(item.name()).append(" x").append(item.qty())
                        .append(" = $").append(item.price() * item.qty()).append("\n");
            }
            receipt.append("Total: $").append(total);
            return receipt.toString();
        }
    }

    static class NotificationService {
        static void send(String customerEmail, String message) {
            System.out.println(message);
            System.out.println("[sent to " + customerEmail + "]");
        }
    }

    static void processOrder(String customerEmail, List<Item> items) {
        OrderValidator.validate(customerEmail, items);
        double total = PriceCalculator.calculateTotal(items);
        String receipt = ReceiptFormatter.format(customerEmail, items, total);
        NotificationService.send(customerEmail, receipt);
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    registerUserTangled() below mixes THREE concerns: validating the
    //    username/password, building a welcome message, and printing it
    //    — the same problem processOrderTangled() had.
    //
    //    Your task:
    //      a) Extract a UsernameValidator.validate(username, password)
    //         method (throws IllegalArgumentException on invalid input).
    //      b) Extract a WelcomeMessageBuilder.build(username) method
    //         that returns the message String.
    //      c) Write registerUser(username, password) that calls both,
    //         then prints the result — update main() to call it.
    //      d) Delete registerUserTangled() once nothing calls it.
    // ======================================================================

    static void registerUserTangled(String username, String password) {
        // validation concern
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
        // message-building concern
        String message = "Welcome, " + username + "! Your account is ready.";
        // printing concern
        System.out.println(message);
    }

    public static void main(String[] args) {
        List<Item> items = List.of(new Item("Mouse", 25.0, 2), new Item("Keyboard", 60.0, 1));

        System.out.println("== BEFORE: validation + math + formatting + sending, all tangled ==");
        processOrderTangled("amina@example.com", items);

        System.out.println();
        System.out.println("== AFTER: four focused pieces, orchestrated by processOrder() ==");
        processOrder("amina@example.com", items);

        System.out.println();
        System.out.println("== TODO exercise: split registerUserTangled() below ==");
        registerUserTangled("amina", "s3cur3pw!");
    }
}
