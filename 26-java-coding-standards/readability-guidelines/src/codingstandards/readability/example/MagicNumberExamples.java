package codingstandards.readability.example;

/** Nulls & magic numbers (the constants half): if (status == 3) means nothing at 2am. */
public class MagicNumberExamples {

    public enum Status {
        PENDING, CLEARED, REJECTED
    }

    // BEFORE: what is "1"? readers have to go find out, or guess
    public static boolean isClearedMagicNumber(int status) {
        return status == 1;
    }

    // AFTER: the name is the documentation
    public static boolean isCleared(Status status) {
        return status == Status.CLEARED;
    }
}
