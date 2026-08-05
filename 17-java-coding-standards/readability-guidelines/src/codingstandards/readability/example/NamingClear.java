package codingstandards.readability.example;

/** AFTER: says everything — a method name that's a question, a parameter that's self-explanatory. */
public class NamingClear {
    public static boolean hasExpired(int daysSincePayment) {
        return daysSincePayment > 30;
    }
}
