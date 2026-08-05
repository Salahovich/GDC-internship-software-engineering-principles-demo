package lawofdemeter.shippinglabel.before;

import lawofdemeter.shippinglabel.example.Address;

/**
 * BEFORE: violates the Law of Demeter. CustomerTrainWreck and
 * OrderTrainWreck expose their internals (getAddress(), getCustomer())
 * so external code can reach through them. Every caller that wants a
 * city has to know the full chain: Order -> Customer -> Address -> city.
 */
public class TrainWreck {

    public static class CustomerTrainWreck {
        private final Address address; // may be null — not every customer has one on file

        public CustomerTrainWreck(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    public static class OrderTrainWreck {
        private final CustomerTrainWreck customer;

        public OrderTrainWreck(CustomerTrainWreck customer) {
            this.customer = customer;
        }

        public CustomerTrainWreck getCustomer() {
            return customer;
        }
    }

    public static String printShippingLabelTrainWreck(OrderTrainWreck order) {
        String city = order.getCustomer().getAddress().getCity(); // reaches through 3 objects
        return "Ship to: " + city;
    }

    // Added later, elsewhere in the codebase — repeats the exact same
    // chain because that's the only way to get at the city from here.
    public static boolean isRegionalDiscountEligibleTrainWreck(OrderTrainWreck order) {
        String city = order.getCustomer().getAddress().getCity(); // same chain, duplicated knowledge
        return city.equals("Casablanca");
    }
}
