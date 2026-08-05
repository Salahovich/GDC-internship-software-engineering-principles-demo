package designpatterns.prototype.ordertemplate.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AFTER: the official Prototype pattern (Refactoring Guru's Java example,
 * applied to orders) — an abstract base with a COPY CONSTRUCTOR, not
 * Java's built-in Cloneable/clone() (which is notoriously easy to get
 * wrong). Every subclass chains its own copy constructor to this one via
 * super(target), and implements the abstract copy().
 *
 * The point isn't "give Order a copy() method" — see StandardOrder and
 * GiftOrder: two DIFFERENT concrete templates. PrototypeDemo.cloneAll()
 * copies a whole List&lt;Order&gt; of both kinds through this one abstract
 * type, with no "if it's a StandardOrder do this, if it's a GiftOrder do
 * that" branch anywhere in the client code.
 */
public abstract class Order {
    protected String customerEmail;
    protected List<String> items;

    protected Order() {
    }

    /** Copy constructor: copies the fields every order has. Subclasses chain to this via super(target). */
    protected Order(Order target) {
        if (target != null) {
            this.customerEmail = target.customerEmail;
            this.items = new ArrayList<>(target.items);
        }
    }

    /** Each subclass returns its own concrete copy — this is the actual Prototype contract. */
    public abstract Order copy();

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // getClass(), not instanceof: keeps equals() symmetric across subclasses
        // (a StandardOrder and a GiftOrder must never report equal to each other).
        if (o == null || getClass() != o.getClass()) return false;
        Order other = (Order) o;
        return Objects.equals(customerEmail, other.customerEmail) && Objects.equals(items, other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerEmail, items);
    }
}
