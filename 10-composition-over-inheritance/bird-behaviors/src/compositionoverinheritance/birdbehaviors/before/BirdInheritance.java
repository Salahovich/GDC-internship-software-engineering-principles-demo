package compositionoverinheritance.birdbehaviors.before;

/**
 * BEFORE: inheritance forces behavior onto every subclass.
 * BirdInheritance assumes all birds fly and quack. Duck fits fine.
 * Penguin doesn't fly — so its only option is to override fly() with
 * something that breaks the promise every other Bird makes (a caller
 * holding a BirdInheritance reference has no way to know THIS one
 * throws).
 */
public class BirdInheritance {
    public String fly() {
        return "Flying high!";
    }

    public String makeSound() {
        return "...";
    }

    public static class DuckInheritance extends BirdInheritance {
        @Override
        public String makeSound() {
            return "Quack!";
        }
    }

    public static class PenguinInheritance extends BirdInheritance {
        @Override
        public String fly() {
            // Penguins can't fly — but fly() was inherited, so it has to
            // be "implemented" somehow. Throwing breaks the Liskov
            // substitution: code that trusts "every Bird can fly()" now
            // crashes on this particular Bird.
            throw new UnsupportedOperationException("Penguins can't fly!");
        }

        @Override
        public String makeSound() {
            return "Squawk!";
        }
    }

    // And reuse is just as awkward: a toy rubber duck also "quacks," but
    // it isn't alive, doesn't lay eggs, and definitely isn't a Bird — so
    // DuckInheritance's quack can't be reused here without either
    // duplicating the string or inheriting from Bird for a toy, which is
    // semantically wrong either way.
}
