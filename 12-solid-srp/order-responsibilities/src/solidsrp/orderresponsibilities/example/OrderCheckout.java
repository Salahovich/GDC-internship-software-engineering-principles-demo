package solidsrp.orderresponsibilities.example;

/** AFTER: orchestrates the four focused pieces. No logic of its own to break. */
public class OrderCheckout {
    public static void checkoutOrder(Order order) {
        OrderCalculator calculator = new OrderCalculator();
        InvoicePrinter printer = new InvoicePrinter();
        OrderRepository repository = new OrderRepository();
        OrderNotifier notifier = new OrderNotifier();

        double total = calculator.calculateTotal(order);
        System.out.println(printer.printInvoice(order, total));
        repository.save(order);
        notifier.sendConfirmation(order);
    }
}
