package designpatterns.visitor.orderitempricing.example;

import java.util.List;

/**
 * BEFORE: pricing logic lives outside the item classes, dispatched with
 * an instanceof chain. Every new item type means editing this class, and
 * every new operation over the same items needs its own instanceof chain.
 */
public class PricingCalculatorInstanceOf {
    public double totalPrice(List<Object> items) {
        double total = 0.0;
        for (Object item : items) {
            if (item instanceof PhysicalItem p) {
                total += p.getBasePrice() + p.getWeightKg() * 2.0;
            } else if (item instanceof DigitalItem d) {
                total += d.getBasePrice();
            } else {
                throw new IllegalArgumentException("Unknown item type: " + item.getClass());
            }
        }
        return total;
    }
}
