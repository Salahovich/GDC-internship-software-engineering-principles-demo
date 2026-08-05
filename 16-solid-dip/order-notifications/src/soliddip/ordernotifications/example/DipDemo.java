package soliddip.ordernotifications.example;

import soliddip.ordernotifications.exercise.OrderRepositoryDipViolation;

import java.util.List;

/**
 * SOLID — D: DEPENDENCY INVERSION PRINCIPLE (DIP)
 * ----------------------------------------------------------------------
 * "High-level modules should not depend on low-level modules — both
 * should depend on abstractions." The order-confirmation notifier
 * introduced back in the SRP demo (the first SOLID letter) is the
 * perfect example: it's a high-level policy ("tell the customer their
 * order is confirmed") that has no business knowing HOW that message
 * gets delivered.
 *
 * This is the last demo in the SOLID series, closing the loop.
 */
public class DipDemo {

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: OrderNotifier is welded to EmailSender ==");
        OrderNotifierDipViolation oldNotifier = new OrderNotifierDipViolation();
        oldNotifier.notifyCustomer(order);
        System.out.println("^ Adding SMS support means editing OrderNotifierDipViolation itself.");

        System.out.println();
        System.out.println("== AFTER: OrderNotifier depends only on the NotificationChannel abstraction ==");
        OrderNotifier emailNotifier = new OrderNotifier(new NotificationChannel.EmailChannel());
        OrderNotifier smsNotifier = new OrderNotifier(new NotificationChannel.SmsChannel());
        emailNotifier.notifyCustomer(order);
        smsNotifier.notifyCustomer(order);
        System.out.println("^ SMS support was added without changing OrderNotifier at all —");
        System.out.println("  that's the whole SOLID series closing the loop.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/OrderRepositoryDipViolation.java ==");
        OrderRepositoryDipViolation oldRepo = new OrderRepositoryDipViolation();
        oldRepo.save(order);
    }
}
