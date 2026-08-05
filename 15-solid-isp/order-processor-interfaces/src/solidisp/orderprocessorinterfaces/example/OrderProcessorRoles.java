package solidisp.orderprocessorinterfaces.example;

/**
 * AFTER: ISP applied. Four small, role-specific interfaces. A class
 * only implements the role it actually plays. A full-service processor
 * can still implement all four when it genuinely needs to; ISP doesn't
 * forbid that, it just stops FORCING it on classes that don't.
 */
public class OrderProcessorRoles {
    public interface TotalCalculable {
        double calculateTotal(Order order);
    }

    public interface Printable {
        String printInvoice(Order order);
    }

    public interface Notifiable {
        void sendConfirmation(Order order);
    }

    public interface Refundable {
        void refund(Order order, double amount);
    }
}
