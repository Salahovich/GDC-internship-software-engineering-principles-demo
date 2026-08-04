package designpatterns.visitor.orderitempricing.example;

/** A shippable good, e.g. a mug. Priced with weight-based shipping. */
public class PhysicalItem {
    private final String name;
    private final double basePrice;
    private final double weightKg;

    public PhysicalItem(String name, double basePrice, double weightKg) {
        this.name = name;
        this.basePrice = basePrice;
        this.weightKg = weightKg;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getWeightKg() {
        return weightKg;
    }

    /** AFTER: double dispatch — hands itself to the visitor's physical-item method. */
    public double accept(OrderItemVisitor visitor) {
        return visitor.visitPhysical(this);
    }
}
