package solidocp.orderdiscounts.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * ShippingCostCalculatorOcpViolation below has the same problem
 * DiscountCalculatorOcpViolation had: adding a new shipping region
 * means editing its if/else chain.
 *
 * Task:
 *   a) Create a ShippingPolicy interface with getFlatRate() (in its own
 *      file).
 *   b) Implement LocalShipping ($3), NationalShipping ($7), and
 *      InternationalShipping ($20).
 *   c) Write a ShippingCostCalculator that takes a ShippingPolicy
 *      instead of a region string.
 *   d) Update OcpDemo.main() to use it, then delete this class.
 */
public class ShippingCostCalculatorOcpViolation {
    public double calculateShipping(String region) {
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
