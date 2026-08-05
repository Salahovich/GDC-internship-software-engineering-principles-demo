# SOLID (I) — Order Processor Interfaces

**Files:** `src/solidisp/orderprocessorinterfaces/`

| Package | File | Role |
|---|---|---|
| `example/` | `OrderItem.java`, `Order.java` | Shared entities |
| `before/` | `OrderProcessorFat.java` | BEFORE — one fat interface + `SimpleReceiptPrinter` |
| `example/` | `OrderProcessorRoles.java` | AFTER — `TotalCalculable`/`Printable`/`Notifiable`/`Refundable` |
| `example/` | `ReceiptPrinter.java` | AFTER — implements only `Printable` |
| `example/` | `FullServiceOrderProcessor.java` | AFTER — implements all four when it genuinely needs to |
| `example/` | `IspDemo.java` | `main()` — runs everything |
| `exercise/` | `SupportDashboardView.java` | TODO exercise — given fat-interface class + `CustomerAccountManagerFat` |

## What it shows

Defining what an "order processor" can do, two ways.

1. **BEFORE (`OrderProcessorFat`)** — one fat interface with
   `calculateTotal`, `printInvoice`, `sendConfirmation`, and `refund`.
   `SimpleReceiptPrinter` only wants to print receipts, but is forced to
   implement the other three anyway — with nothing sensible to do but
   throw.
2. **AFTER (`TotalCalculable`, `Printable`, `Notifiable`,
   `Refundable`)** — four small, role-specific interfaces.
   `ReceiptPrinter` implements only `Printable`. `FullServiceOrderProcessor`
   can still implement all four when it genuinely needs to — ISP doesn't
   forbid that, it just stops forcing it on classes that don't.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`CustomerAccountManagerFat` (see `exercise/SupportDashboardView.java`)
has the same problem: `SupportDashboardView` only wants to look up order
history, but is forced to implement `updateProfile()` and
`deleteAccount()` too.

**Task:** split `CustomerAccountManagerFat` into `ProfileEditable`
(`updateProfile`), `OrderHistoryViewable` (`viewOrderHistory`), and
`AccountDeletable` (`deleteAccount`). Make a new
`ReadOnlySupportDashboard` implement only `OrderHistoryViewable`. Update
`IspDemo.main` to use it, then delete `SupportDashboardView` and
`CustomerAccountManagerFat`.

<details>
<summary>Solution</summary>

```java
public interface ProfileEditable {
    void updateProfile(String customerEmail, String newName);
}

public interface OrderHistoryViewable {
    String viewOrderHistory(String customerEmail);
}

public interface AccountDeletable {
    void deleteAccount(String customerEmail);
}

public class ReadOnlySupportDashboard implements OrderHistoryViewable {
    public String viewOrderHistory(String customerEmail) {
        return "3 past orders for " + customerEmail;
    }
}

// in main():
ReadOnlySupportDashboard dashboard = new ReadOnlySupportDashboard();
System.out.println(dashboard.viewOrderHistory("amina@example.com"));
```

</details>
