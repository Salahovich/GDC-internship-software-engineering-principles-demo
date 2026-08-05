package compositionoverinheritance.birdbehaviors.example;

/** AFTER: sound is a small, swappable behavior instead of an inherited method. */
public interface SoundBehavior {
    String makeSound();

    class Quack implements SoundBehavior {
        public String makeSound() {
            return "Quack!";
        }
    }

    class Squawk implements SoundBehavior {
        public String makeSound() {
            return "Squawk!";
        }
    }
}
