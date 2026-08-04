package codingstandards.readability.example;

/** Naming: a good name removes the need for the comment you were about to write. */
public class NamingExamples {

    // BEFORE: says nothing — what does "process" do? what is "d"?
    public static boolean process(int d) {
        return d > 30;
    }

    // AFTER: says everything — a method name that's a question, a parameter that's self-explanatory
    public static boolean hasExpired(int daysSincePayment) {
        return daysSincePayment > 30;
    }
}
