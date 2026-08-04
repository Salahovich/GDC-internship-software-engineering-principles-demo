# Singleton — Store Config

**Files:** `src/designpatterns/singleton/storeconfig/`

| Package | File | Role |
|---|---|---|
| `example/` | `StoreConfigNoSingleton.java` | BEFORE — plain class, anyone can `new` it |
| `example/` | `StoreConfig.java` | AFTER — the Singleton |
| `example/` | `SingletonDemo.java` | `main()` — runs everything |
| `exercise/` | `RequestIdGeneratorTodo.java` | TODO exercise instructions |

## What it shows

Two "copies" of the store's discount rate, two ways.

1. **BEFORE (`StoreConfigNoSingleton`)** — a plain class. Two `new`
   instances mean two independent discount rates: updating one doesn't
   touch the other.
2. **AFTER (`StoreConfig`)** — private constructor + a single static
   `getInstance()`. Every caller gets back the exact same object, so an
   update is visible everywhere immediately.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

The order service needs a request ID generator: every call should return
a new, increasing number, shared across the whole app (see
`exercise/RequestIdGeneratorTodo.java` for the full brief).

**Task:** write `RequestIdGenerator` as a Singleton — same shape as
`StoreConfig` — with a `nextId()` method that increments a shared
counter. Confirm calls from two different places keep counting up
instead of resetting.

<details>
<summary>Solution</summary>

```java
public class RequestIdGenerator {
    private static final RequestIdGenerator INSTANCE = new RequestIdGenerator();
    private int counter = 0;

    private RequestIdGenerator() {}

    public static RequestIdGenerator getInstance() {
        return INSTANCE;
    }

    public int nextId() {
        counter++;
        return counter;
    }
}
```

</details>
