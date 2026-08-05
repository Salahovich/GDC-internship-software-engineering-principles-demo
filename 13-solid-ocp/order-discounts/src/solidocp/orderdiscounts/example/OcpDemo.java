package solidocp.orderdiscounts.example;

import solidocp.orderdiscounts.exercise.ShippingCostCalculatorOcpViolation;

import java.util.List;

/**
 * SOLID — O: OPEN/CLOSED PRINCIPLE (OCP)
 * ----------------------------------------------------------------------
 * "Software entities should be open for extension, but closed for
 * modification." Adding a new case should mean adding new code, not
 * editing code that already works (and was already tested).
 *
 * Continuing the same Order / OrderItem entities from the SRP demo: this
 * demo calculates a discounted total two ways.
 */
public class OcpDemo {

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
        System.out.println("VIP total: $" + calculator.calculateTotal(order, new DiscountPolicy.VipDiscount()));
        System.out.println("Student total: $" + calculator.calculateTotal(order, new DiscountPolicy.StudentDiscount()));
        System.out.println("Regular total: $" + calculator.calculateTotal(order, new DiscountPolicy.RegularDiscount()));

        System.out.println();
        System.out.println("== TODO exercise: see exercise/ShippingCostCalculatorOcpViolation.java ==");
        ShippingCostCalculatorOcpViolation shipping = new ShippingCostCalculatorOcpViolation();
        System.out.println("Local shipping: $" + shipping.calculateShipping("LOCAL"));
        System.out.println("International shipping: $" + shipping.calculateShipping("INTERNATIONAL"));
    }
}
