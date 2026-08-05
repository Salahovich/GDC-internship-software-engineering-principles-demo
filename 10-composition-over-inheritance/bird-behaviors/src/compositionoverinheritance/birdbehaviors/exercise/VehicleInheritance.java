package compositionoverinheritance.birdbehaviors.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * VehicleInheritance below has the exact same problem BirdInheritance
 * had: it assumes every vehicle has an engine to start. BicycleInheritance
 * doesn't, so it's forced to override startEngine() with an exception —
 * the same broken promise Penguin had.
 *
 * Task:
 *   a) Create an EngineBehavior interface with a start() method (in its
 *      own file).
 *   b) Implement GasEngine (returns "Vroom!") and NoEngine (returns
 *      "Pedaling power only.").
 *   c) Create a Vehicle class that takes an EngineBehavior in its
 *      constructor and delegates startEngine() to it.
 *   d) Update CompositionOverInheritanceDemo.main() to build a car and
 *      a bicycle using Vehicle + the right EngineBehavior, then delete
 *      this class.
 */
public class VehicleInheritance {
    public String startEngine() {
        return "Vroom!";
    }

    public static class CarInheritance extends VehicleInheritance {
        // fits fine — cars have engines
    }

    public static class BicycleInheritance extends VehicleInheritance {
        @Override
        public String startEngine() {
            throw new UnsupportedOperationException("Bicycles don't have an engine!");
        }
    }
}
