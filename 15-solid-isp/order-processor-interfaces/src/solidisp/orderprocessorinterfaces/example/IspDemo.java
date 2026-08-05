package solidisp.orderprocessorinterfaces.example;

import solidisp.orderprocessorinterfaces.exercise.SupportDashboardView;

import java.util.List;

/**
 * SOLID — I: INTERFACE SEGREGATION PRINCIPLE (ISP)
 * ----------------------------------------------------------------------
 * "Clients shouldn't be forced to depend on methods they don't use." A
 * fat interface bundling unrelated operations forces every implementer
 * to deal with all of them — usually by throwing an exception for the
 * ones that don't apply, which breaks any caller that trusted the
 * interface's full contract (the same trap the LSP demo covered from the
 * other direction).
 */
public class IspDemo {

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: SimpleReceiptPrinter is forced to fake three methods it can't support ==");
        OrderProcessorFat.SimpleReceiptPrinter oldPrinter = new OrderProcessorFat.SimpleReceiptPrinter();
        System.out.println(oldPrinter.printInvoice(order));
        try {
            oldPrinter.calculateTotal(order);
        } catch (UnsupportedOperationException e) {
            System.out.println("calculateTotal() crashed: " + e.getMessage());
        }

        System.out.println();
        System.out.println("== AFTER: ReceiptPrinter only implements the one role it plays ==");
        ReceiptPrinter printer = new ReceiptPrinter();
        System.out.println(printer.printInvoice(order));

        FullServiceOrderProcessor fullService = new FullServiceOrderProcessor();
        System.out.println("Full-service total: $" + fullService.calculateTotal(order));
        fullService.sendConfirmation(order);
        fullService.refund(order, 10.0);

        System.out.println();
        System.out.println("== TODO exercise: see exercise/SupportDashboardView.java ==");
        SupportDashboardView dashboard = new SupportDashboardView();
        System.out.println(dashboard.viewOrderHistory("amina@example.com"));
        try {
            dashboard.updateProfile("amina@example.com", "Amina K.");
        } catch (UnsupportedOperationException e) {
            System.out.println("updateProfile() crashed: " + e.getMessage());
        }
    }
}
