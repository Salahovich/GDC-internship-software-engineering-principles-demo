package leastastonishment.shoppingcart.example;

import leastastonishment.shoppingcart.exercise.EmailValidatorSurprising;

/**
 * LEAST ASTONISHMENT (Principle of Least Surprise)
 * ----------------------------------------------------------------------
 * A method should behave the way its name and signature promise. A
 * caller reading `cart.getItemsSortedByPrice()` expects a sorted VIEW —
 * not a call that quietly rearranges the cart itself. Surprising side
 * effects (or a predicate that throws instead of returning false) cause
 * bugs that are hard to trace, because the code "worked" exactly as its
 * name suggested — just not as its actual behavior was.
 */
public class LeastAstonishmentDemo {

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
        System.out.println("== TODO exercise: see exercise/EmailValidatorSurprising.java ==");
        try {
            System.out.println(EmailValidatorSurprising.isValidEmailSurprising(null));
        } catch (NullPointerException e) {
            System.out.println("isValidEmailSurprising(null) threw NPE — surprising for a boolean predicate!");
        }
    }
}
