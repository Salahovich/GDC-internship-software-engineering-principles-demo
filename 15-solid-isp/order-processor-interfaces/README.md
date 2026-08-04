# SOLID (I) — Order Processor Interfaces

**File:** `src/solidisp/orderprocessorinterfaces/IspDemo.java`

## What it shows

Defining what an "order processor" can do, two ways.

1. **BEFORE (`OrderProcessor`)** — one fat interface with
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

`CustomerAccountManager` (bottom of the file, marked `TODO`) has the
same problem: `SupportDashboardView` only wants to look up order
history, but is forced to implement `updateProfile()` and
`deleteAccount()` too.

**Task:** split `CustomerAccountManager` into `ProfileEditable`
(`updateProfile`), `OrderHistoryViewable` (`viewOrderHistory`), and
`AccountDeletable` (`deleteAccount`). Make a new
`ReadOnlySupportDashboard` implement only `OrderHistoryViewable`. Update
`main` to use it, then delete `SupportDashboardView` and
`CustomerAccountManager`.

<details>
<summary>Solution</summary>

```java
interface ProfileEditable {
    void updateProfile(String customerEmail, String newName);
}

interface OrderHistoryViewable {
    String viewOrderHistory(String customerEmail);
}

interface AccountDeletable {
    void deleteAccount(String customerEmail);
}

static class ReadOnlySupportDashboard implements OrderHistoryViewable {
    public String viewOrderHistory(String customerEmail) {
        return "3 past orders for " + customerEmail;
    }
}

// in main():
ReadOnlySupportDashboard dashboard = new ReadOnlySupportDashboard();
System.out.println(dashboard.viewOrderHistory("amina@example.com"));
```

</details>
