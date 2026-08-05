package lawofdemeter.shippinglabel.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * printManagerIdTrainWreck() below has the same problem as
 * example/TrainWreck.java: it reaches through Order -> Warehouse ->
 * Employee to read a manager's id.
 *
 * Task:
 *   a) Add a getManagerId() method to Warehouse that delegates to
 *      manager.getId().
 *   b) Add a getWarehouseManagerId() method to OrderWithWarehouse that
 *      delegates to warehouse.getManagerId().
 *   c) Update LawOfDemeterDemo.main() to call
 *      order.getWarehouseManagerId() instead of reaching through the
 *      chain directly, then delete printManagerIdTrainWreck().
 */
public class WarehouseManagerTrainWreck {

    public static class Employee {
        private final String id;

        public Employee(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static class Warehouse {
        private final Employee manager;

        public Warehouse(Employee manager) {
            this.manager = manager;
        }

        public Employee getManager() {
            return manager;
        }
    }

    public static class OrderWithWarehouse {
        private final Warehouse warehouse;

        public OrderWithWarehouse(Warehouse warehouse) {
            this.warehouse = warehouse;
        }

        public Warehouse getWarehouse() {
            return warehouse;
        }
    }

    public static String printManagerIdTrainWreck(OrderWithWarehouse order) {
        return order.getWarehouse().getManager().getId(); // reaches through 2 objects
    }
}
