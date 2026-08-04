package designpatterns.visitor.orderitempricing.example;

import java.util.ArrayList;
import java.util.List;

/**
 * VISITOR PATTERN
 * ----------------------------------------------------------------------
 * Lets you add new operations over a family of types without touching
 * the types themselves, using double dispatch: accept() calls back into
 * the right visitor method for that type.
 */
public class VisitorDemo {

    public static void main(String[] args) {
        PhysicalItem mug = new PhysicalItem("Ceramic Mug", 12.0, 0.4);
        DigitalItem ebook = new DigitalItem("Java 21 Field Guide (ebook)", 9.0);

        System.out.println("== BEFORE: PricingCalculatorInstanceOf uses an instanceof chain ==");
        List<Object> rawItems = new ArrayList<>(List.of(mug, ebook));
        System.out.println("Total: $" + new PricingCalculatorInstanceOf().totalPrice(rawItems));
        System.out.println("^ Adding a new item type means editing this class's instanceof chain.");

        System.out.println();
        System.out.println("== AFTER: PricingVisitor — same math, double dispatch instead of instanceof ==");
        List<Object> items = new ArrayList<>(List.of(mug, ebook));
        System.out.println("Total: $" + new PricingVisitor().total(items));
        System.out.println("^ Same total, no instanceof — each item called back into the right visit method.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/ShippingLabelVisitorTodo.java ==");
    }
}
