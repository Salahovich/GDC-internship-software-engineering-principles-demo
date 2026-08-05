package compositionoverinheritance.birdbehaviors.example;

/**
 * AFTER: composition also buys reuse across UNRELATED types. A toy
 * rubber duck isn't a Bird at all, but it can hold the exact same
 * Quack behavior object a real Duck uses — no shared base class
 * required.
 */
public class Toy {
    private final SoundBehavior soundBehavior;

    public Toy(SoundBehavior soundBehavior) {
        this.soundBehavior = soundBehavior;
    }

    public String squeeze() {
        return soundBehavior.makeSound();
    }
}
