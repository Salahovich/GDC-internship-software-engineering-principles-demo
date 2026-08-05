package cohesionandcoupling.storeoperations.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * ReceiptPrinterLowCohesion below bundles TWO unrelated jobs: formatting
 * a receipt line and tracking a running loyalty-points balance. A
 * caller that only wants to format text is still forced to depend on
 * (and construct) the loyalty points store.
 *
 * Task:
 *   a) Split it into ReceiptFormatter (formatLine(itemName, price)) and
 *      LoyaltyPointsTracker (addPoints(customerId, points),
 *      getPoints(customerId)) — each in its own file.
 *   b) Update CohesionAndCouplingDemo.main() to use the two new classes
 *      directly instead of ReceiptPrinterLowCohesion.
 *   c) Delete this class once nothing uses it.
 *
 * Goal: notice how splitting the unrelated responsibility apart means a
 * "just print a receipt line" caller no longer needs to know loyalty
 * points exist at all — cohesion went up, coupling went down.
 */
public class ReceiptPrinterLowCohesion {
    private final Map<String, Integer> loyaltyPoints = new HashMap<>();

    public String formatLine(String itemName, double price) {
        return " - " + itemName + ": $" + price;
    }

    public void addPoints(String customerId, int points) {
        loyaltyPoints.merge(customerId, points, Integer::sum);
    }

    public int getPoints(String customerId) {
        return loyaltyPoints.getOrDefault(customerId, 0);
    }
}
