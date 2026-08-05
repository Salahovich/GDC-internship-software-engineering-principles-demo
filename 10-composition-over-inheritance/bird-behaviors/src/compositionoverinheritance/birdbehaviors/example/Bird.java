package compositionoverinheritance.birdbehaviors.example;

/**
 * AFTER: composition applied. A Bird is built from whichever behaviors
 * actually apply to it — nothing is forced, nothing needs to be
 * overridden with an exception. A Penguin is just a Bird composed with
 * CannotFly — no override, no exception, no broken promise.
 */
public class Bird {
    private final FlyBehavior flyBehavior;
    private final SoundBehavior soundBehavior;

    public Bird(FlyBehavior flyBehavior, SoundBehavior soundBehavior) {
        this.flyBehavior = flyBehavior;
        this.soundBehavior = soundBehavior;
    }

    public String fly() {
        return flyBehavior.fly();
    }

    public String makeSound() {
        return soundBehavior.makeSound();
    }
}
