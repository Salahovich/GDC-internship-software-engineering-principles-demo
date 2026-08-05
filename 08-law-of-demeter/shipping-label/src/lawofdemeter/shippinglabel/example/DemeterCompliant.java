package lawofdemeter.shippinglabel.example;

/**
 * AFTER: Law of Demeter applied. Each object exposes what its IMMEDIATE
 * neighbor needs, and only talks to its own direct collaborator: Order
 * only talks to Customer (getShippingCity()); Customer only talks to
 * Address (getCity()). Callers never see Address at all, and the "no
 * address on file" case is handled in exactly one place.
 */
public class DemeterCompliant {

    public static class Customer {
        private final Address address;

        public Customer(Address address) {
            this.address = address;
        }

        public String getCity() {
            return address != null ? address.getCity() : "Unknown";
        }
    }

    public static class Order {
        private final Customer customer;

        public Order(Customer customer) {
            this.customer = customer;
        }

        public String getShippingCity() {
            return customer.getCity();
        }
    }

    public static String printShippingLabel(Order order) {
        return "Ship to: " + order.getShippingCity();
    }

    public static boolean isRegionalDiscountEligible(Order order) {
        return order.getShippingCity().equals("Casablanca");
    }
}
