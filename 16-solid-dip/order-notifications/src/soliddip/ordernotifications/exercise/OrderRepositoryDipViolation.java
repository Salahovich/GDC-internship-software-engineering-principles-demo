package soliddip.ordernotifications.exercise;

import soliddip.ordernotifications.example.Order;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * OrderRepositoryDipViolation below has the same problem
 * OrderNotifierDipViolation had: it directly constructs a concrete
 * MySqlDatabase — the high-level "save this order" policy is welded to
 * one specific database.
 *
 * Task:
 *   a) Create a Database interface with a save(String data) method (in
 *      its own file).
 *   b) Make MySqlDatabase implement it, and add a second implementation,
 *      InMemoryDatabase, that just stores strings in a List (handy for
 *      tests).
 *   c) Create an OrderRepository that takes a Database in its
 *      constructor and delegates save() to it.
 *   d) Update DipDemo.main() to build an OrderRepository with each
 *      Database implementation, then delete this class.
 */
public class OrderRepositoryDipViolation {

    public static class MySqlDatabase {
        public void save(String data) {
            System.out.println("[MySQL] saved: " + data);
        }
    }

    private final MySqlDatabase database = new MySqlDatabase(); // hard-wired to one concrete database

    public void save(Order order) {
        database.save("order for " + order.getCustomerEmail());
    }
}
