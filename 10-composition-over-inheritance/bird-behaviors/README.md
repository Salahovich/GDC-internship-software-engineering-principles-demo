# Composition over Inheritance — Bird Behaviors

**Files:** `src/compositionoverinheritance/birdbehaviors/`

| Package | File | Role |
|---|---|---|
| `before/` | `BirdInheritance.java` | BEFORE — inheritance forces `fly()` on every subclass |
| `example/` | `FlyBehavior.java`, `SoundBehavior.java` | AFTER — small swappable behaviors |
| `example/` | `Bird.java`, `Toy.java` | AFTER — composed types |
| `example/` | `CompositionOverInheritanceDemo.java` | `main()` — runs everything |
| `exercise/` | `VehicleInheritance.java` | TODO exercise — given inheritance hierarchy |

## What it shows

Modeling flying and sound-making birds, two ways.

1. **BEFORE (`BirdInheritance.DuckInheritance` / `PenguinInheritance`)**
   — the base class hands every bird a `fly()` method. `Duck` fits
   fine, but `Penguin` can't fly, so its only option is to override
   `fly()` with an exception — breaking the promise that "every `Bird`
   can `fly()`" for any code that trusts the base type. Reuse is
   awkward too: a toy rubber duck also "quacks," but it isn't a `Bird`
   at all, so it can't reuse `Duck`'s sound without inheriting from
   `Bird` for a toy (semantically wrong) or duplicating the string (DRY
   violation).
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

`VehicleInheritance` (see `exercise/VehicleInheritance.java`) has the
same problem: `BicycleInheritance` is forced to override `startEngine()`
with an exception, because bicycles don't have an engine.

**Task:** create an `EngineBehavior` interface with `start()`, implement
`GasEngine` (`"Vroom!"`) and `NoEngine` (`"Pedaling power only."`), and a
`Vehicle` class that takes an `EngineBehavior` and delegates
`startEngine()` to it. Update `CompositionOverInheritanceDemo.main` to
build a car and a bicycle with `Vehicle` + the right behavior, then
delete `VehicleInheritance`.

<details>
<summary>Solution</summary>

```java
public interface EngineBehavior {
    String start();

    class GasEngine implements EngineBehavior {
        public String start() { return "Vroom!"; }
    }

    class NoEngine implements EngineBehavior {
        public String start() { return "Pedaling power only."; }
    }
}

public class Vehicle {
    private final EngineBehavior engineBehavior;

    public Vehicle(EngineBehavior engineBehavior) {
        this.engineBehavior = engineBehavior;
    }

    public String startEngine() {
        return engineBehavior.start();
    }
}

// in main():
Vehicle car = new Vehicle(new EngineBehavior.GasEngine());
Vehicle bicycle = new Vehicle(new EngineBehavior.NoEngine());
System.out.println("Car: " + car.startEngine());
System.out.println("Bicycle: " + bicycle.startEngine());
```

</details>
