package cohesionandcoupling.storeoperations.example;

import java.util.Map;

/** AFTER: high cohesion, low coupling — the payroll concern alone. */
public class PayrollCalculator {
    private final Map<String, Double> hourlyPayRates;

    public PayrollCalculator(Map<String, Double> hourlyPayRates) {
        this.hourlyPayRates = hourlyPayRates;
    }

    public double calculatePay(String employeeRole, double hoursWorked) {
        double rate = hourlyPayRates.getOrDefault(employeeRole, 0.0);
        return rate * hoursWorked;
    }
}
