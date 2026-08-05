package solidocp.orderdiscounts.example;

/**
 * AFTER: OCP applied. Each customer type is its own DiscountPolicy.
 * Adding "EMPLOYEE" tomorrow means writing a new EmployeeDiscount class
 * — OrderTotalCalculator itself never needs to change again.
 */
public interface DiscountPolicy {
    double getDiscountPercent();

    class RegularDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 0; }
    }

    class VipDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 10; }
    }

    class StudentDiscount implements DiscountPolicy {
        public double getDiscountPercent() { return 15; }
    }
}
