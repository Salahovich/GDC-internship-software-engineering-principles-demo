package leastastonishment.shoppingcart.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * isValidEmailSurprising() below is named like a predicate — a caller
 * expects it to always return true or false. Instead, it throws a
 * NullPointerException when given null, surprising any caller who
 * (reasonably) expected to write `if (isValidEmail(userInput))` without
 * a try/catch.
 *
 * Task: write isValidEmail(String email) (in its own file) that returns
 * false for null or blank input instead of throwing, and update
 * LeastAstonishmentDemo.main() to call it. Delete this class once
 * nothing calls it.
 */
public class EmailValidatorSurprising {

    public static boolean isValidEmailSurprising(String email) {
        return email.contains("@") && email.contains("."); // NPE if email is null
    }
}
