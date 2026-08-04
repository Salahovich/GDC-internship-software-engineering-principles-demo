package designpatterns.visitor.orderitempricing.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Warehouse ops wants a second operation: printing shipping labels. Only
 * PhysicalItem actually ships; DigitalItem doesn't need a label.
 *
 * Task: write `ShippingLabelVisitor implements OrderItemVisitor` (in its
 * own file, next to this one) that:
 *   - visitPhysical(item): print "Ship <name> (<weightKg> kg)", return 1
 *   - visitDigital(item): print "<name> is a digital download — no
 *     shipping label needed", return 0
 *
 * You should not need to touch PhysicalItem, DigitalItem, or
 * PricingVisitor to add this new operation — that's the payoff of double
 * dispatch: new operations are new classes, not edits to existing ones.
 *
 * Wire it up the same way PricingVisitor is used: item.accept(new ShippingLabelVisitor()).
 */
public class ShippingLabelVisitorTodo {
}
