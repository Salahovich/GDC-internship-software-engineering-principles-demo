package codingstandards.readability.example;

/** AFTER: the name is the documentation. */
public class StatusEnumAfter {
    public enum Status {
        PENDING, CLEARED, REJECTED
    }

    public static boolean isCleared(Status status) {
        return status == Status.CLEARED;
    }
}
