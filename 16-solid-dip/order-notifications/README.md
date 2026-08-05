# SOLID (D) — Order Notifications

**Files:** `src/soliddip/ordernotifications/`

| Package | File | Role |
|---|---|---|
| `example/` | `OrderItem.java`, `Order.java` | Shared entities |
| `before/` | `OrderNotifierDipViolation.java` | BEFORE — high-level policy constructs a concrete `EmailSender` |
| `example/` | `NotificationChannel.java` | AFTER — interface + `EmailChannel`/`SmsChannel` |
| `example/` | `OrderNotifier.java` | AFTER — depends only on `NotificationChannel` |
| `example/` | `DipDemo.java` | `main()` — runs everything |
| `exercise/` | `OrderRepositoryDipViolation.java` | TODO exercise — given class welded to `MySqlDatabase` |

## What it shows

Notifying a customer their order is confirmed, two ways — returning to
the `OrderNotifier` first introduced in
[`12-solid-srp/`](../../12-solid-srp/order-responsibilities/README.md)
to close the SOLID series.

1. **BEFORE (`OrderNotifierDipViolation`)** — a high-level policy
   ("notify the customer") directly constructs a concrete `EmailSender`.
   Adding SMS support means editing the notifier itself.
2. **AFTER (`OrderNotifier` + `NotificationChannel`)** — `OrderNotifier`
   depends only on the `NotificationChannel` abstraction, never a
   concrete sender. `EmailChannel` and `SmsChannel` both implement it,
   and `OrderNotifier` never has to change to support either.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`OrderRepositoryDipViolation` (see
`exercise/OrderRepositoryDipViolation.java`) has the same problem: it
directly constructs a concrete `MySqlDatabase`.

**Task:** create a `Database` interface with `save(String data)`,
implement `MySqlDatabase` and a second implementation `InMemoryDatabase`
(stores strings in a `List`, handy for tests), and an `OrderRepository`
that takes a `Database` in its constructor. Update `DipDemo.main` to
build an `OrderRepository` with each implementation, then delete
`OrderRepositoryDipViolation`.

<details>
<summary>Solution</summary>

```java
public interface Database {
    void save(String data);
}

public class MySqlDatabaseImpl implements Database {
    public void save(String data) {
        System.out.println("[MySQL] saved: " + data);
    }
}

public class InMemoryDatabase implements Database {
    private final List<String> records = new ArrayList<>();

    public void save(String data) {
        records.add(data);
        System.out.println("[in-memory] saved: " + data);
    }
}

public class OrderRepository {
    private final Database database;

    public OrderRepository(Database database) {
        this.database = database;
    }

    public void save(Order order) {
        database.save("order for " + order.getCustomerEmail());
    }
}

// in main():
OrderRepository repo = new OrderRepository(new InMemoryDatabase());
repo.save(order);
```

</details>
