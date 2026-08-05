package cohesionandcoupling.storeoperations.example;

import java.util.HashMap;
import java.util.Map;

/**
 * BEFORE: low cohesion, high coupling. StoreManagerLowCohesion does
 * three UNRELATED jobs: inventory, payroll, and promotional email.
 * Nothing about "restocking a shelf" has anything to do with
 * "calculating a paycheck" or "emailing a coupon" — they're only
 * together because they all happen to relate to "running a store."
 *
 * The cost shows up in its constructor: ANY caller that wants this
 * class — even one that only ever calls restockItem() — is forced to
 * depend on (and construct/wire up) a pay rate table and an email
 * sender address too. That's coupling that has nothing to do with what
 * the caller actually needed.
 */
public class StoreManagerLowCohesion {
    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, Double> hourlyPayRates; // payroll concern
    private final String emailSenderAddress;           // email concern

    public StoreManagerLowCohesion(Map<String, Double> hourlyPayRates, String emailSenderAddress) {
        this.hourlyPayRates = hourlyPayRates;
        this.emailSenderAddress = emailSenderAddress;
    }

    // -- inventory concern --
    public void restockItem(String item, int qty) {
        stock.merge(item, qty, Integer::sum);
    }

    public int getStock(String item) {
        return stock.getOrDefault(item, 0);
    }

    // -- payroll concern --
    public double calculatePay(String employeeRole, double hoursWorked) {
        double rate = hourlyPayRates.getOrDefault(employeeRole, 0.0);
        return rate * hoursWorked;
    }

    // -- promotional email concern --
    public void sendPromotionEmail(String customerEmail, String promoCode) {
        System.out.println("[" + emailSenderAddress + " -> " + customerEmail + "] Use code " + promoCode + " for 10% off!");
    }
}
