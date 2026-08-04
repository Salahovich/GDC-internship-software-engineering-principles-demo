package lawofdemeter.shippinglabel;

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
 *
 * This demo reads a shipping city two ways: BEFORE, callers reach
 * through Order -> Customer -> Address directly, duplicated across two
 * call sites, one of which crashes with a NullPointerException when a
 * customer has no address on file; AFTER, each object only talks to its
 * immediate collaborator, and the "no address" case is handled once.
 */
public class LawOfDemeterDemo {

    static class Address {
        private final String city;

        Address(String city) {
            this.city = city;
        }

        String getCity() {
            return city;
        }
    }

    // ======================================================================
    // 1) BEFORE — violates the Law of Demeter.
    //    CustomerTrainWreck and OrderTrainWreck expose their internals
    //    (getAddress(), getCustomer()) so external code can reach through
    //    them. Every caller that wants a city has to know the full chain:
    //    Order -> Customer -> Address -> city.
    // ======================================================================

    static class CustomerTrainWreck {
        private final Address address; // may be null — not every customer has one on file

        CustomerTrainWreck(Address address) {
            this.address = address;
        }

        Address getAddress() {
            return address;
        }
    }

    static class OrderTrainWreck {
        private final CustomerTrainWreck customer;

        OrderTrainWreck(CustomerTrainWreck customer) {
            this.customer = customer;
        }

        CustomerTrainWreck getCustomer() {
            return customer;
        }
    }

    static String printShippingLabelTrainWreck(OrderTrainWreck order) {
        String city = order.getCustomer().getAddress().getCity(); // reaches through 3 objects
        return "Ship to: " + city;
    }

    // Added later, elsewhere in the codebase — repeats the exact same
    // chain because that's the only way to get at the city from here.
    static boolean isRegionalDiscountEligibleTrainWreck(OrderTrainWreck order) {
        String city = order.getCustomer().getAddress().getCity(); // same chain, duplicated knowledge
        return city.equals("Casablanca");
    }

    // ======================================================================
    // 2) AFTER — Law of Demeter applied.
    //    Each object exposes what its IMMEDIATE neighbor needs, and only
    //    talks to its own direct collaborator:
    //      Order   only talks to Customer   (getShippingCity())
    //      Customer only talks to Address   (getCity())
    //    Callers never see Address at all, and the "no address on file"
    //    case is handled in exactly one place.
    // ======================================================================

    static class Customer {
        private final Address address;

        Customer(Address address) {
            this.address = address;
        }

        String getCity() {
            return address != null ? address.getCity() : "Unknown";
        }
    }

    static class Order {
        private final Customer customer;

        Order(Customer customer) {
            this.customer = customer;
        }

        String getShippingCity() {
            return customer.getCity();
        }
    }

    static String printShippingLabel(Order order) {
        return "Ship to: " + order.getShippingCity();
    }

    static boolean isRegionalDiscountEligible(Order order) {
        return order.getShippingCity().equals("Casablanca");
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    printManagerIdTrainWreck() below has the same problem: it reaches
    //    through Order -> Warehouse -> Employee to read a manager's id.
    //
    //    Your task:
    //      a) Add a getManagerId() method to Warehouse that delegates to
    //         manager.getId().
    //      b) Add a getWarehouseManagerId() method to OrderTrainWreck
    //         (or a new Order-like class) that delegates to
    //         warehouse.getManagerId().
    //      c) Update main() to call order.getWarehouseManagerId()
    //         instead of reaching through the chain directly, then
    //         delete printManagerIdTrainWreck().
    // ======================================================================

    static class Employee {
        private final String id;

        Employee(String id) {
            this.id = id;
        }

        String getId() {
            return id;
        }
    }

    static class Warehouse {
        private final Employee manager;

        Warehouse(Employee manager) {
            this.manager = manager;
        }

        Employee getManager() {
            return manager;
        }
    }

    static class OrderWithWarehouse {
        private final Warehouse warehouse;

        OrderWithWarehouse(Warehouse warehouse) {
            this.warehouse = warehouse;
        }

        Warehouse getWarehouse() {
            return warehouse;
        }
    }

    static String printManagerIdTrainWreck(OrderWithWarehouse order) {
        return order.getWarehouse().getManager().getId(); // reaches through 2 objects
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: callers reach through Order -> Customer -> Address ==");
        OrderTrainWreck orderWithAddress =
                new OrderTrainWreck(new CustomerTrainWreck(new Address("Casablanca")));
        System.out.println(printShippingLabelTrainWreck(orderWithAddress));
        System.out.println("Discount eligible: " + isRegionalDiscountEligibleTrainWreck(orderWithAddress));

        OrderTrainWreck orderNoAddress = new OrderTrainWreck(new CustomerTrainWreck(null));
        try {
            printShippingLabelTrainWreck(orderNoAddress);
        } catch (NullPointerException e) {
            System.out.println("Crashed: NullPointerException — the caller had to know Address could be null,");
            System.out.println("and nobody checked, because that knowledge lives 2 hops away from here.");
        }

        System.out.println();
        System.out.println("== AFTER: Order only talks to Customer, Customer only talks to Address ==");
        Order safeOrder = new Order(new Customer(new Address("Casablanca")));
        System.out.println(printShippingLabel(safeOrder));
        System.out.println("Discount eligible: " + isRegionalDiscountEligible(safeOrder));

        Order safeOrderNoAddress = new Order(new Customer(null));
        System.out.println(printShippingLabel(safeOrderNoAddress) + "  <- handled once, inside Customer, no crash");

        System.out.println();
        System.out.println("== TODO exercise: fix the train wreck below ==");
        OrderWithWarehouse warehouseOrder = new OrderWithWarehouse(new Warehouse(new Employee("EMP-42")));
        System.out.println("Manager id: " + printManagerIdTrainWreck(warehouseOrder));
    }
}
