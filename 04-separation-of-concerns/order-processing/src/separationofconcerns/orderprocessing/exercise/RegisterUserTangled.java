package separationofconcerns.orderprocessing.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * registerUserTangled() below mixes THREE concerns: validating the
 * username/password, building a welcome message, and printing it — the
 * same problem OrderProcessingTangled had.
 *
 * Task:
 *   a) Extract a UsernameValidator.validate(username, password) method
 *      (throws IllegalArgumentException on invalid input).
 *   b) Extract a WelcomeMessageBuilder.build(username) method that
 *      returns the message String.
 *   c) Write registerUser(username, password) that calls both, then
 *      prints the result — update SeparationOfConcernsDemo.main() to
 *      call it.
 *   d) Delete this class once nothing calls it.
 */
public class RegisterUserTangled {

    public static void registerUserTangled(String username, String password) {
        // validation concern
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
        // message-building concern
        String message = "Welcome, " + username + "! Your account is ready.";
        // printing concern
        System.out.println(message);
    }
}
