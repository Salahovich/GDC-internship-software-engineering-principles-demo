package solidocp.orderdiscounts;

import java.util.List;

/**
 * SOLID — O: OPEN/CLOSED PRINCIPLE (OCP)
 * ----------------------------------------------------------------------
 * "Software entities should be open for extension, but closed for
 * modification." Adding a new case should mean adding new code, not
 * editing code that already works (and was already tested).
 *
 * Continuing the same Order / OrderItem entities from the SRP demo: this
 * demo calculates a discounted total two ways. BEFORE, a calculator
 * branches on the customer type with if/else — adding a new customer
 * type means opening up and editing this class. AFTER, each customer
 * type is its own DiscountPolicy implementation — adding a new type
 * means adding a new class, and the calculator never changes again.
 */
public class OcpDemo {

    // ======================================================================
    // Shared entities — same as the SRP demo.
    // ======================================================================

    static class OrderItem {
        private final String name;
        private final double price;
        private final int qty;

        OrderItem(String name, double price, int qty) {
            this.name = name;
            this.price = price;
            this.qty = qty;
        }

        double getLineTotal() { return price * qty; }
    }

    static class Order {
        private final String customerEmail;
        private final List<OrderItem> items;

        Order(String customerEmail, List<OrderItem> items) {
            this.customerEmail = customerEmail;
            this.items = items;
        }

        String getCustomerEmail() { return customerEmail; }

        double getSubtotal() {
            return items.stream().mapToDouble(OrderItem::getLineTotal).sum();
        }
    }

    // ======================================================================
    // 1) BEFORE — violates OCP.
    //    DiscountCalculatorOcpViolation has to be MODIFIED every time a
    //    new customer type is added. The class is never "done" — it's
    //    permanently open to editing.
    // ======================================================================

    static class DiscountCalculatorOcpViolation {
        double calculateTotal(Order order, String customerType) {
            double subtotal = order.getSubtotal();
            double discountPercent;
            if (customerType.equals("REGULAR")) {
                discountPercent = 0;
            } else if (customerType.equals("VIP")) {
                discountPercent = 10;
            } else if (customerType.equals("STUDENT")) {
                discountPercent = 15;
            } else {
                throw new IllegalArgumentException("unknown customer type: " + customerType);
            }
            return subtotal * (1 - discountPercent / 100);
        }
    }

    // ======================================================================
    // 2) AFTER — OCP applied.
    //    Each customer type is its own DiscountPolicy. Adding "EMPLOYEE"
    //    tomorrow means writing a new EmployeeDiscount class —
    //    OrderTotalCalculator itself never needs to change again.
    // ======================================================================

    interface DiscountPolicy {
        double getDiscountPercent();
    }

    static class RegularDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 0; }
    }

    static class VipDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 10; }
    }

    static class StudentDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 15; }
    }

    static class OrderTotalCalculator {
        double calculateTotal(Order order, DiscountPolicy policy) {
            double subtotal = order.getSubtotal();
            return subtotal * (1 - policy.getDiscountPercent() / 100);
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    ShippingCostCalculatorOcpViolation below has the same problem:
    //    adding a new shipping region means editing its if/else chain.
    //
    //    Your task:
    //      a) Create a ShippingPolicy interface with getFlatRate().
    //      b) Implement LocalShipping ($3), NationalShipping ($7), and
    //         InternationalShipping ($20).
    //      c) Write a ShippingCostCalculator that takes a ShippingPolicy
    //         instead of a region string.
    //      d) Update main() to use it, then delete
    //         ShippingCostCalculatorOcpViolation.
    // ======================================================================

    static class ShippingCostCalculatorOcpViolation {
        double calculateShipping(String region) {
            if (region.equals("LOCAL")) {
                return 3.0;
            } else if (region.equals("NATIONAL")) {
                return 7.0;
            } else if (region.equals("INTERNATIONAL")) {
                return 20.0;
            }
            throw new IllegalArgumentException("unknown region: " + region);
        }
    }

    public static void main(String[] args) {
        Order order = new Order("amina@example.com",
                List.of(new OrderItem("Mouse", 25.0, 2), new OrderItem("Keyboard", 60.0, 1)));

        System.out.println("== BEFORE: adding a new customer type means editing this class ==");
        DiscountCalculatorOcpViolation oldCalculator = new DiscountCalculatorOcpViolation();
        System.out.println("VIP total: $" + oldCalculator.calculateTotal(order, "VIP"));
        System.out.println("Student total: $" + oldCalculator.calculateTotal(order, "STUDENT"));

        System.out.println();
        System.out.println("== AFTER: new customer types are new classes, calculator never changes ==");
        OrderTotalCalculator calculator = new OrderTotalCalculator();
        System.out.println("VIP total: $" + calculator.calculateTotal(order, new VipDiscount()));
        System.out.println("Student total: $" + calculator.calculateTotal(order, new StudentDiscount()));
        System.out.println("Regular total: $" + calculator.calculateTotal(order, new RegularDiscount()));

        System.out.println();
        System.out.println("== TODO exercise: fix ShippingCostCalculatorOcpViolation below ==");
        ShippingCostCalculatorOcpViolation shipping = new ShippingCostCalculatorOcpViolation();
        System.out.println("Local shipping: $" + shipping.calculateShipping("LOCAL"));
        System.out.println("International shipping: $" + shipping.calculateShipping("INTERNATIONAL"));
    }
}
