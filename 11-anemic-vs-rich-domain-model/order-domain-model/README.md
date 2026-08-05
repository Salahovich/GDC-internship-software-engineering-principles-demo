# Anemic vs. Rich Domain Model — Order Domain Model

**Files:** `src/anemicvsrich/orderdomainmodel/`

| Package | File | Role |
|---|---|---|
| `before/` | `OrderAnemic.java` | BEFORE — bag of fields + external service owning the rule |
| `example/` | `Order.java` | AFTER — rich object, no setter to bypass |
| `example/` | `AnemicVsRichDemo.java` | `main()` — runs everything |
| `exercise/` | `AccountAnemic.java` | TODO exercise — given anemic class + service |

## What it shows

Building an order's total, two ways.

1. **BEFORE (`OrderAnemic` + `OrderAnemic.OrderServiceAnemic`)** —
   `OrderAnemic` is a plain bag of fields. The discount rule (0-100%)
   lives entirely in `OrderServiceAnemic`. That works as long as
   everyone goes through the service — but `OrderAnemic.setDiscountPercent()`
   is still public, so any other code can call it directly, skip the
   rule entirely, and set a nonsensical 500% discount.
2. **AFTER (`Order`)** — the rule lives inside `applyDiscount()`.
   There's no `setDiscountPercent()` to bypass at all — the only door
   in is the one that checks the rule.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`AccountAnemic` / `AccountAnemic.AccountServiceAnemic` (see
`exercise/AccountAnemic.java`) has the same problem: the "can't
overdraw" rule lives in the service, but `setBalance()` is public and
bypasses it.

**Task:** create a rich `Account` class (in its own file) with a
private balance and a `withdraw(double amount)` method that checks the
balance and throws `IllegalStateException` if it's too large — no
public setter. Update `AnemicVsRichDemo.main` to call
`account.withdraw(amount)` directly, then delete `AccountAnemic`.

<details>
<summary>Solution</summary>

```java
public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("insufficient balance");
        }
        balance -= amount;
    }
}

// in main():
Account account = new Account(50.0);
account.withdraw(20);
System.out.println("Balance: $" + account.getBalance());
```

</details>

## Isn't this the same demo as Tell, Don't Ask? Aren't they contradictory?

Good instinct to notice the overlap — but it's not a contradiction, it's
the same idea at two different scales.

**Tell, Don't Ask** is the design guideline: don't reach into an object,
read its state, decide something outside it, then push a new value back
in — tell the object what you want and let it enforce its own rules.

**Anemic Domain Model** is not a competing principle sitting opposite
Tell, Don't Ask. It's the *name for what a whole class looks like when
nobody applied Tell, Don't Ask to it*: every field has a public
getter/setter, and every rule about that data has leaked out into a
separate "service" class. **Rich Domain Model** is simply what you get
when a domain class *does* follow Tell, Don't Ask consistently — the
object owns its rules, and there's no setter left for outside code to
sneak around them.

So the two ideas agree, not conflict: Tell, Don't Ask is the rule; Rich
vs. Anemic is what your domain classes look like depending on whether you
followed it.

Where it gets genuinely nuanced: not every object in a system is (or
should be) a rich domain object. Plain data-transfer objects (API
request/response bodies), most ORM/JPA entities, and query-side read
models in a CQRS design are *supposed* to be simple data holders —
they're not carrying business rules, just moving structured data across
a boundary, and frameworks often require the public no-arg
constructor/getter/setter shape to (de)serialize them. Being anemic is
fine, even correct, for that role. The anti-pattern Fowler warned about
is specifically when your **core business logic** ends up scattered
across service classes acting on anemic objects that were supposed to be
your domain model — that's where you lose encapsulation, and often end
up back at a DRY problem too, as the same rule gets re-checked (or
forgotten) in every service method that touches the object.
