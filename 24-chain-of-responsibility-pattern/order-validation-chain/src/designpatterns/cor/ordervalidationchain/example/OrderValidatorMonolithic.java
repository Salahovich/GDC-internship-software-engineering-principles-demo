package designpatterns.cor.ordervalidationchain.example;

/** BEFORE: every check crammed into one method. Adding a new check means editing this. */
public class OrderValidatorMonolithic {
    public boolean validate(Order order) {
        if (order.getCustomerEmail() == null || !order.getCustomerEmail().contains("@")) {
            System.out.println("[Monolithic] Rejected: invalid email");
            return false;
        }
        if (order.getItems().isEmpty()) {
            System.out.println("[Monolithic] Rejected: no items");
            return false;
        }
        if (!order.isInStock()) {
            System.out.println("[Monolithic] Rejected: out of stock");
            return false;
        }
        System.out.println("[Monolithic] Order passed all checks");
        return true;
    }
}
