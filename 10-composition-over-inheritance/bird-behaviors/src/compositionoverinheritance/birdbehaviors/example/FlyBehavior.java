package compositionoverinheritance.birdbehaviors.example;

/** AFTER: flying is a small, swappable behavior instead of an inherited method. */
public interface FlyBehavior {
    String fly();

    class FlyWithWings implements FlyBehavior {
        public String fly() {
            return "Flying high!";
        }
    }

    class CannotFly implements FlyBehavior {
        public String fly() {
            return "I cannot fly.";
        }
    }
}
