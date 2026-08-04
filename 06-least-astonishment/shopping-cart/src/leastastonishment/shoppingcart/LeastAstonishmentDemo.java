package leastastonishment.shoppingcart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LEAST ASTONISHMENT (Principle of Least Surprise)
 * ----------------------------------------------------------------------
 * A method should behave the way its name and signature promise. A
 * caller reading `cart.getItemsSortedByPrice()` expects a sorted VIEW —
 * not a call that quietly rearranges the cart itself. Surprising side
 * effects (or a predicate that throws instead of returning false) cause
 * bugs that are hard to trace, because the code "worked" exactly as its
 * name suggested — just not as its actual behavior was.
 *
 * This demo shows a cart's "sorted view" getter that secretly mutates
 * the cart in place (BEFORE), then a version that returns a new sorted
 * copy and leaves the cart untouched (AFTER).
 */
public class LeastAstonishmentDemo {

    // ======================================================================
    // 1) BEFORE — violates least astonishment.
    //    getItemsSortedByPriceSurprising() looks like an innocent getter,
    //    but it sorts the cart's OWN internal list in place before
    //    returning it. Any caller who just wanted "a sorted view for
    //    display" has permanently reordered the actual cart — checkout
    //    order, receipt order, everything downstream is now different,
    //    with nothing in the method's name warning that would happen.
    // ======================================================================

    static class ShoppingCartSurprising {
        private final List<String> items =
                new ArrayList<>(List.of("Keyboard($60)", "Mouse($25)", "Monitor($200)"));

        List<String> getItems() {
            return items;
        }

        // Looks read-only. Isn't.
        List<String> getItemsSortedByPriceSurprising() {
            Collections.sort(items); // mutates the field in place!
            return items;
        }
    }

    // ======================================================================
    // 2) AFTER — least astonishment applied.
    //    getItemsSortedByPrice() returns a NEW sorted copy. The cart's
    //    own order is untouched, and the caller can freely modify the
    //    returned list without corrupting the cart's internal state
    //    either (getItems() also returns a defensive copy, for the same
    //    reason).
    // ======================================================================

    static class ShoppingCart {
        private final List<String> items =
                new ArrayList<>(List.of("Keyboard($60)", "Mouse($25)", "Monitor($200)"));

        List<String> getItems() {
            return List.copyOf(items); // defensive copy — no hidden aliasing either
        }

        List<String> getItemsSortedByPrice() {
            List<String> copy = new ArrayList<>(items);
            Collections.sort(copy);
            return copy; // original cart order is untouched
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    isValidEmailSurprising() below is named like a predicate — a
    //    caller expects it to always return true or false. Instead, it
    //    throws a NullPointerException when given null, surprising any
    //    caller who (reasonably) expected to write
    //    `if (isValidEmailSurprising(userInput))` without a try/catch.
    //
    //    Your task: write isValidEmail(String email) that returns false
    //    for null or blank input instead of throwing, and update main()
    //    to call it. Delete isValidEmailSurprising() once nothing calls
    //    it.
    // ======================================================================

    static boolean isValidEmailSurprising(String email) {
        return email.contains("@") && email.contains("."); // NPE if email is null
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: an innocent-looking getter secretly reorders the cart ==");
        ShoppingCartSurprising surprisingCart = new ShoppingCartSurprising();
        System.out.println("Original order:       " + surprisingCart.getItems());
        surprisingCart.getItemsSortedByPriceSurprising(); // caller just wanted a sorted view...
        System.out.println("Order after 'getter': " + surprisingCart.getItems() + "  <- permanently reordered!");

        System.out.println();
        System.out.println("== AFTER: sorted view is a copy, the cart's own order is untouched ==");
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Original order: " + cart.getItems());
        System.out.println("Sorted view:    " + cart.getItemsSortedByPrice());
        System.out.println("Order after:    " + cart.getItems() + "  <- unchanged, as a getter should be");

        System.out.println();
        System.out.println("== TODO exercise: fix isValidEmailSurprising() below ==");
        try {
            System.out.println(isValidEmailSurprising(null));
        } catch (NullPointerException e) {
            System.out.println("isValidEmailSurprising(null) threw NPE — surprising for a boolean predicate!");
        }
    }
}
