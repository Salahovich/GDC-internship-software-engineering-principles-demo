# SOLID (D) — Order Notifications

**File:** `src/soliddip/ordernotifications/DipDemo.java`

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

`OrderRepositoryDipViolation` (bottom of the file, marked `TODO`) has the
same problem: it directly constructs a concrete `MySqlDatabase`.

**Task:** create a `Database` interface with `save(String data)`,
implement `MySqlDatabase` and a second implementation `InMemoryDatabase`
(stores strings in a `List`, handy for tests), and an `OrderRepository`
that takes a `Database` in its constructor. Update `main` to build an
`OrderRepository` with each implementation, then delete
`OrderRepositoryDipViolation`.

<details>
<summary>Solution</summary>

```java
interface Database {
    void save(String data);
}

static class MySqlDatabaseImpl implements Database {
    public void save(String data) {
        System.out.println("[MySQL] saved: " + data);
    }
}

static class InMemoryDatabase implements Database {
    private final List<String> records = new ArrayList<>();

    public void save(String data) {
        records.add(data);
        System.out.println("[in-memory] saved: " + data);
    }
}

static class OrderRepository {
    private final Database database;

    OrderRepository(Database database) {
        this.database = database;
    }

    void save(Order order) {
        database.save("order for " + order.getCustomerEmail());
    }
}

// in main():
OrderRepository repo = new OrderRepository(new InMemoryDatabase());
repo.save(order);
```

</details>
