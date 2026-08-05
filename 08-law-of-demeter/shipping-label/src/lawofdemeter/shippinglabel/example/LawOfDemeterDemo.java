package lawofdemeter.shippinglabel.example;

import lawofdemeter.shippinglabel.exercise.WarehouseManagerTrainWreck;

/**
 * LAW OF DEMETER ("don't talk to strangers")
 * ----------------------------------------------------------------------
 * A method should only call methods on: itself, its own parameters,
 * objects it creates, or its direct fields/collaborators — not reach
 * THROUGH one object to grab another, and then another. Chains like
 * `order.getCustomer().getAddress().getCity()` ("train wrecks") couple
 * the caller to the internal structure of every object along the way.
 * Change how Customer stores its address, and every caller with a chain
 * like this breaks — or worse, crashes when a link in the chain is null.
 */
public class LawOfDemeterDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: callers reach through Order -> Customer -> Address ==");
        TrainWreck.OrderTrainWreck orderWithAddress =
                new TrainWreck.OrderTrainWreck(new TrainWreck.CustomerTrainWreck(new Address("Casablanca")));
        System.out.println(TrainWreck.printShippingLabelTrainWreck(orderWithAddress));
        System.out.println("Discount eligible: " + TrainWreck.isRegionalDiscountEligibleTrainWreck(orderWithAddress));

        TrainWreck.OrderTrainWreck orderNoAddress =
                new TrainWreck.OrderTrainWreck(new TrainWreck.CustomerTrainWreck(null));
        try {
            TrainWreck.printShippingLabelTrainWreck(orderNoAddress);
        } catch (NullPointerException e) {
            System.out.println("Crashed: NullPointerException — the caller had to know Address could be null,");
            System.out.println("and nobody checked, because that knowledge lives 2 hops away from here.");
        }

        System.out.println();
        System.out.println("== AFTER: Order only talks to Customer, Customer only talks to Address ==");
        DemeterCompliant.Order safeOrder =
                new DemeterCompliant.Order(new DemeterCompliant.Customer(new Address("Casablanca")));
        System.out.println(DemeterCompliant.printShippingLabel(safeOrder));
        System.out.println("Discount eligible: " + DemeterCompliant.isRegionalDiscountEligible(safeOrder));

        DemeterCompliant.Order safeOrderNoAddress =
                new DemeterCompliant.Order(new DemeterCompliant.Customer(null));
        System.out.println(DemeterCompliant.printShippingLabel(safeOrderNoAddress) + "  <- handled once, inside Customer, no crash");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/WarehouseManagerTrainWreck.java ==");
        WarehouseManagerTrainWreck.OrderWithWarehouse warehouseOrder =
                new WarehouseManagerTrainWreck.OrderWithWarehouse(
                        new WarehouseManagerTrainWreck.Warehouse(new WarehouseManagerTrainWreck.Employee("EMP-42")));
        System.out.println("Manager id: " + WarehouseManagerTrainWreck.printManagerIdTrainWreck(warehouseOrder));
    }
}
