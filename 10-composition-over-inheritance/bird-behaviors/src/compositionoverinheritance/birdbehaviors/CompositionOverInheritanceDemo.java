package compositionoverinheritance.birdbehaviors;

/**
 * COMPOSITION OVER INHERITANCE
 * ----------------------------------------------------------------------
 * Prefer building behavior by PLUGGING IN small, interchangeable pieces
 * (composition) over inheriting it from a shared base class. A class
 * hierarchy locks every subclass into whatever the base class provides
 * — if one subclass doesn't actually fit that behavior, you end up
 * overriding it with something that breaks the "is-a" promise (or
 * throws). Composition lets you assemble exactly the behavior a type
 * needs, and reuse that same behavior in completely unrelated types.
 *
 * This demo models birds two ways: BEFORE, a Bird base class hands every
 * subclass a fly() method whether it can fly or not, forcing Penguin to
 * override it with an exception; AFTER, flying and sound are injected
 * behaviors, so a Penguin simply doesn't get a "flying" behavior, and
 * the exact same "quack" behavior can be reused by something that isn't
 * even a bird.
 */
public class CompositionOverInheritanceDemo {

    // ======================================================================
    // 1) BEFORE — inheritance forces behavior onto every subclass.
    //    BirdInheritance assumes all birds fly and quack. Duck fits
    //    fine. Penguin doesn't fly — so its only option is to override
    //    fly() with something that breaks the promise every other Bird
    //    makes (a caller holding a BirdInheritance reference has no way
    //    to know THIS one throws).
    // ======================================================================

    static class BirdInheritance {
        String fly() {
            return "Flying high!";
        }

        String makeSound() {
            return "...";
        }
    }

    static class DuckInheritance extends BirdInheritance {
        @Override
        String makeSound() {
            return "Quack!";
        }
    }

    static class PenguinInheritance extends BirdInheritance {
        @Override
        String fly() {
            // Penguins can't fly — but fly() was inherited, so it has to
            // be "implemented" somehow. Throwing breaks the Liskov
            // substitution: code that trusts "every Bird can fly()" now
            // crashes on this particular Bird.
            throw new UnsupportedOperationException("Penguins can't fly!");
        }

        @Override
        String makeSound() {
            return "Squawk!";
        }
    }

    // And reuse is just as awkward: a toy rubber duck also "quacks," but
    // it isn't alive, doesn't lay eggs, and definitely isn't a Bird — so
    // DuckInheritance's quack can't be reused here without either
    // duplicating the string or inheriting from Bird for a toy, which is
    // semantically wrong either way.

    // ======================================================================
    // 2) AFTER — composition applied.
    //    Flying and sound are small, swappable behaviors. A Bird is
    //    built from whichever behaviors actually apply to it — nothing
    //    is forced, nothing needs to be overridden with an exception.
    // ======================================================================

    interface FlyBehavior {
        String fly();
    }

    static class FlyWithWings implements FlyBehavior {
        public String fly() {
            return "Flying high!";
        }
    }

    static class CannotFly implements FlyBehavior {
        public String fly() {
            return "I cannot fly.";
        }
    }

    interface SoundBehavior {
        String makeSound();
    }

    static class Quack implements SoundBehavior {
        public String makeSound() {
            return "Quack!";
        }
    }

    static class Squawk implements SoundBehavior {
        public String makeSound() {
            return "Squawk!";
        }
    }

    static class Bird {
        private final FlyBehavior flyBehavior;
        private final SoundBehavior soundBehavior;

        Bird(FlyBehavior flyBehavior, SoundBehavior soundBehavior) {
            this.flyBehavior = flyBehavior;
            this.soundBehavior = soundBehavior;
        }

        String fly() {
            return flyBehavior.fly();
        }

        String makeSound() {
            return soundBehavior.makeSound();
        }
    }

    // A Penguin is just a Bird composed with CannotFly — no override, no
    // exception, no broken promise. Every Bird's fly() always returns a
    // sensible answer.

    // Composition also buys reuse across UNRELATED types: a toy rubber
    // duck isn't a Bird at all, but it can hold the exact same Quack
    // behavior object a real Duck uses — no shared base class required.
    static class Toy {
        private final SoundBehavior soundBehavior;

        Toy(SoundBehavior soundBehavior) {
            this.soundBehavior = soundBehavior;
        }

        String squeeze() {
            return soundBehavior.makeSound();
        }
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    VehicleInheritance below has the exact same problem: it assumes
    //    every vehicle has an engine to start. BicycleInheritance
    //    doesn't, so it's forced to override startEngine() with an
    //    exception — the same broken promise Penguin had.
    //
    //    Your task:
    //      a) Create an EngineBehavior interface with a start() method.
    //      b) Implement GasEngine (returns "Vroom!") and NoEngine
    //         (returns "Pedaling power only.").
    //      c) Create a Vehicle class that takes an EngineBehavior in its
    //         constructor and delegates startEngine() to it.
    //      d) Update main() to build a car and a bicycle using Vehicle +
    //         the right EngineBehavior, then delete VehicleInheritance,
    //         CarInheritance, and BicycleInheritance.
    // ======================================================================

    static class VehicleInheritance {
        String startEngine() {
            return "Vroom!";
        }
    }

    static class CarInheritance extends VehicleInheritance {
        // fits fine — cars have engines
    }

    static class BicycleInheritance extends VehicleInheritance {
        @Override
        String startEngine() {
            throw new UnsupportedOperationException("Bicycles don't have an engine!");
        }
    }

    public static void main(String[] args) {
        System.out.println("== BEFORE: Penguin inherits fly() and has to override it with an exception ==");
        DuckInheritance duck = new DuckInheritance();
        System.out.println("Duck: " + duck.fly() + " " + duck.makeSound());

        PenguinInheritance penguin = new PenguinInheritance();
        try {
            System.out.println("Penguin: " + penguin.fly());
        } catch (UnsupportedOperationException e) {
            System.out.println("Penguin: fly() crashed — " + e.getMessage());
        }
        System.out.println("^ Code that trusts \"every Bird can fly()\" breaks the moment it meets a Penguin.");

        System.out.println();
        System.out.println("== AFTER: behaviors are composed in, nothing is forced ==");
        Bird composedDuck = new Bird(new FlyWithWings(), new Quack());
        Bird composedPenguin = new Bird(new CannotFly(), new Squawk());
        System.out.println("Duck: " + composedDuck.fly() + " " + composedDuck.makeSound());
        System.out.println("Penguin: " + composedPenguin.fly() + " " + composedPenguin.makeSound());
        System.out.println("^ No override, no exception — every Bird's fly() returns a sensible answer.");

        Toy rubberDuck = new Toy(new Quack());
        System.out.println("Rubber duck (not a Bird at all): " + rubberDuck.squeeze()
                + "  <- reuses the exact same Quack behavior");

        System.out.println();
        System.out.println("== TODO exercise: fix VehicleInheritance below ==");
        CarInheritance car = new CarInheritance();
        System.out.println("Car: " + car.startEngine());
        BicycleInheritance bicycle = new BicycleInheritance();
        try {
            System.out.println("Bicycle: " + bicycle.startEngine());
        } catch (UnsupportedOperationException e) {
            System.out.println("Bicycle: startEngine() crashed — " + e.getMessage());
        }
    }
}
