package designpatterns.visitor.orderitempricing.example;

import java.util.List;

/** AFTER: pricing, expressed as a visitor. Same rules, no instanceof, no casts. */
public class PricingVisitor implements OrderItemVisitor {

    @Override
    public double visitPhysical(PhysicalItem item) {
        return item.getBasePrice() + item.getWeightKg() * 2.0;
    }

    @Override
    public double visitDigital(DigitalItem item) {
        return item.getBasePrice();
    }

    public double total(List<?> items) {
        double total = 0.0;
        for (Object item : items) {
            if (item instanceof PhysicalItem p) {
                total += p.accept(this);
            } else if (item instanceof DigitalItem d) {
                total += d.accept(this);
            }
        }
        return total;
    }
}
