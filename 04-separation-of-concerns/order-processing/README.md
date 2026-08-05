# Separation of Concerns — Order Processing

**Files:** `src/separationofconcerns/orderprocessing/`

| Package | File | Role |
|---|---|---|
| `example/` | `Item.java` | Shared entity |
| `before/` | `OrderProcessingTangled.java` | BEFORE — all four concerns in one method |
| `example/` | `OrderValidator.java`, `PriceCalculator.java`, `ReceiptFormatter.java`, `NotificationService.java` | AFTER — one concern each |
| `example/` | `OrderProcessor.java` | AFTER — orchestrates the four pieces |
| `example/` | `SeparationOfConcernsDemo.java` | `main()` — runs everything |
| `exercise/` | `RegisterUserTangled.java` | TODO exercise — given tangled code |

## What it shows

Processing an order — validate, total the price, format a receipt, send
it — two ways.

1. **BEFORE (`OrderProcessingTangled.processOrderTangled`)** — one
   method does all four things. Changing how the receipt looks means
   editing the same method that also validates input and computes
   totals.
2. **AFTER (`OrderProcessor.processOrder`)** — four focused pieces
   (`OrderValidator`, `PriceCalculator`, `ReceiptFormatter`,
   `NotificationService`), each owning exactly one concern.
   `processOrder` just calls them in order.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`RegisterUserTangled.registerUserTangled` (see
`exercise/RegisterUserTangled.java`) mixes three concerns: validating
the username/password, building a welcome message, and printing it.

**Task:** split it into `UsernameValidator.validate(username, password)`,
`WelcomeMessageBuilder.build(username)`, and a `registerUser(username,
password)` that calls both and prints the result. Update
`SeparationOfConcernsDemo.main` to call `registerUser`, then delete
`RegisterUserTangled`.

<details>
<summary>Solution</summary>

```java
public class UsernameValidator {
    public static void validate(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
    }
}

public class WelcomeMessageBuilder {
    public static String build(String username) {
        return "Welcome, " + username + "! Your account is ready.";
    }
}

// in main():
UsernameValidator.validate("amina", "s3cur3pw!");
System.out.println(WelcomeMessageBuilder.build("amina"));
```

</details>
