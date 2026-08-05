package compositionoverinheritance.birdbehaviors.example;

import compositionoverinheritance.birdbehaviors.before.BirdInheritance;

import compositionoverinheritance.birdbehaviors.exercise.VehicleInheritance;

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
 */
public class CompositionOverInheritanceDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: Penguin inherits fly() and has to override it with an exception ==");
        BirdInheritance.DuckInheritance duck = new BirdInheritance.DuckInheritance();
        System.out.println("Duck: " + duck.fly() + " " + duck.makeSound());

        BirdInheritance.PenguinInheritance penguin = new BirdInheritance.PenguinInheritance();
        try {
            System.out.println("Penguin: " + penguin.fly());
        } catch (UnsupportedOperationException e) {
            System.out.println("Penguin: fly() crashed — " + e.getMessage());
        }
        System.out.println("^ Code that trusts \"every Bird can fly()\" breaks the moment it meets a Penguin.");

        System.out.println();
        System.out.println("== AFTER: behaviors are composed in, nothing is forced ==");
        Bird composedDuck = new Bird(new FlyBehavior.FlyWithWings(), new SoundBehavior.Quack());
        Bird composedPenguin = new Bird(new FlyBehavior.CannotFly(), new SoundBehavior.Squawk());
        System.out.println("Duck: " + composedDuck.fly() + " " + composedDuck.makeSound());
        System.out.println("Penguin: " + composedPenguin.fly() + " " + composedPenguin.makeSound());
        System.out.println("^ No override, no exception — every Bird's fly() returns a sensible answer.");

        Toy rubberDuck = new Toy(new SoundBehavior.Quack());
        System.out.println("Rubber duck (not a Bird at all): " + rubberDuck.squeeze()
                + "  <- reuses the exact same Quack behavior");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/VehicleInheritance.java ==");
        VehicleInheritance.CarInheritance car = new VehicleInheritance.CarInheritance();
        System.out.println("Car: " + car.startEngine());
        VehicleInheritance.BicycleInheritance bicycle = new VehicleInheritance.BicycleInheritance();
        try {
            System.out.println("Bicycle: " + bicycle.startEngine());
        } catch (UnsupportedOperationException e) {
            System.out.println("Bicycle: startEngine() crashed — " + e.getMessage());
        }
    }
}
