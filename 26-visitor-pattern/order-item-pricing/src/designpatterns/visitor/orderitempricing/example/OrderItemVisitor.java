package designpatterns.visitor.orderitempricing.example;

/**
 * AFTER: the visitor interface — one method per item type. A new
 * operation means a new class implementing this interface, not a change
 * to any item class or any existing operation.
 */
public interface OrderItemVisitor {
    double visitPhysical(PhysicalItem item);
    double visitDigital(DigitalItem item);
}
