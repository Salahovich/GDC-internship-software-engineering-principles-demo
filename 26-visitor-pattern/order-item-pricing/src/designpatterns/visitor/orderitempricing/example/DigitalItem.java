package designpatterns.visitor.orderitempricing.example;

/** A downloadable good, e.g. an ebook. No shipping, ever. */
public class DigitalItem {
    private final String name;
    private final double basePrice;

    public DigitalItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    /** AFTER: double dispatch — hands itself to the visitor's digital-item method. */
    public double accept(OrderItemVisitor visitor) {
        return visitor.visitDigital(this);
    }
}
