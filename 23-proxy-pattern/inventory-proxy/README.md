# Proxy — Inventory Proxy

**Files:** `src/designpatterns/proxy/inventoryproxy/`

| Package | File | Role |
|---|---|---|
| `example/` | `InventoryLookup.java` | The abstraction both sides depend on |
| `example/` | `RealInventoryService.java` | BEFORE — the "expensive" real service (tracks call count) |
| `example/` | `CachingInventoryProxy.java` | AFTER — caches lookups |
| `example/` | `ProxyDemo.java` | `main()` — runs everything |
| `exercise/` | `OrderSaver.java`, `RealOrderRepository.java` | Given interface + real subject |
| `exercise/` | `LoggingProxyTodo.java` | TODO exercise instructions |

## What it shows

Looking up stock for the same SKU three times during a checkout
(validate, reserve, confirm), two ways.

1. **BEFORE (`RealInventoryService`)** — every lookup hits the "real"
   service directly. Three lookups, three expensive calls.
2. **AFTER (`CachingInventoryProxy`)** — implements the same
   `InventoryLookup` interface, so callers can't tell the difference, but
   only calls through to the real service the first time; the other two
   lookups are served from cache.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

Ops wants every order save logged, without changing
`RealOrderRepository` itself (see `exercise/LoggingProxyTodo.java` for
the given classes).

**Task:** write `LoggingOrderSaverProxy implements OrderSaver` that
wraps a `RealOrderRepository`, logs before delegating to
`save(orderId)` — the same shape as `CachingInventoryProxy`.

<details>
<summary>Solution</summary>

```java
public class LoggingOrderSaverProxy implements OrderSaver {
    private final RealOrderRepository realRepository;

    public LoggingOrderSaverProxy(RealOrderRepository realRepository) {
        this.realRepository = realRepository;
    }

    public void save(String orderId) {
        System.out.println("[LoggingProxy] Saving order " + orderId + " at " + System.currentTimeMillis());
        realRepository.save(orderId);
    }
}
```

</details>
