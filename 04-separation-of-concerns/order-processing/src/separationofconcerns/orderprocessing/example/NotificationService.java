package separationofconcerns.orderprocessing.example;

/** AFTER: SoC applied — the "sending" concern, and only that concern. */
public class NotificationService {
    public static void send(String customerEmail, String message) {
        System.out.println(message);
        System.out.println("[sent to " + customerEmail + "]");
    }
}
