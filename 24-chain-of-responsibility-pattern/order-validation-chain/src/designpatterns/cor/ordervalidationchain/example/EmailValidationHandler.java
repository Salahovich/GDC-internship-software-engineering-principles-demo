package designpatterns.cor.ordervalidationchain.example;

public class EmailValidationHandler extends OrderValidationHandler {
    @Override
    protected boolean check(Order order) {
        boolean ok = order.getCustomerEmail() != null && order.getCustomerEmail().contains("@");
        if (!ok) {
            System.out.println("[EmailValidationHandler] Rejected: invalid email");
        }
        return ok;
    }
}
