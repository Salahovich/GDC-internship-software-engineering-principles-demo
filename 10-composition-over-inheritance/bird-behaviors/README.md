# Composition over Inheritance — Bird Behaviors

**File:** `src/compositionoverinheritance/birdbehaviors/CompositionOverInheritanceDemo.java`

## What it shows

Modeling flying and sound-making birds, two ways.

1. **BEFORE (`BirdInheritance` / `DuckInheritance` / `PenguinInheritance`)**
   — the base class hands every bird a `fly()` method. `Duck` fits fine,
   but `Penguin` can't fly, so its only option is to override `fly()`
   with an exception — breaking the promise that "every `Bird` can
   `fly()`" for any code that trusts the base type. Reuse is awkward
   too: a toy rubber duck also "quacks," but it isn't a `Bird` at all, so
   it can't reuse `Duck`'s sound without inheriting from `Bird` for a toy
   (semantically wrong) or duplicating the string (DRY violation).
2. **AFTER (`Bird` + `FlyBehavior` + `SoundBehavior`)** — flying and
   sound are small, swappable behaviors composed into `Bird` at
   construction time. `Penguin` is just `new Bird(new CannotFly(), new
   Squawk())` — no override, no exception, no broken promise. And the
   exact same `Quack` behavior is reused by an unrelated `Toy` class,
   with no shared base class required.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`VehicleInheritance` (bottom of the file, marked `TODO`) has the same
problem: `BicycleInheritance` is forced to override `startEngine()` with
an exception, because bicycles don't have an engine.

**Task:** create an `EngineBehavior` interface with `start()`, implement
`GasEngine` (`"Vroom!"`) and `NoEngine` (`"Pedaling power only."`), and a
`Vehicle` class that takes an `EngineBehavior` and delegates
`startEngine()` to it. Update `main` to build a car and a bicycle with
`Vehicle` + the right behavior, then delete `VehicleInheritance`,
`CarInheritance`, and `BicycleInheritance`.

<details>
<summary>Solution</summary>

```java
interface EngineBehavior {
    String start();
}

static class GasEngine implements EngineBehavior {
    public String start() {
        return "Vroom!";
    }
}

static class NoEngine implements EngineBehavior {
    public String start() {
        return "Pedaling power only.";
    }
}

static class Vehicle {
    private final EngineBehavior engineBehavior;

    Vehicle(EngineBehavior engineBehavior) {
        this.engineBehavior = engineBehavior;
    }

    String startEngine() {
        return engineBehavior.start();
    }
}

// in main():
Vehicle car = new Vehicle(new GasEngine());
Vehicle bicycle = new Vehicle(new NoEngine());
System.out.println("Car: " + car.startEngine());
System.out.println("Bicycle: " + bicycle.startEngine());
```

</details>
