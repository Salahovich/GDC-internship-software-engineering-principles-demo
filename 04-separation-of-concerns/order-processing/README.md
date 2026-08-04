# Separation of Concerns — Order Processing

**File:** `src/separationofconcerns/orderprocessing/SeparationOfConcernsDemo.java`

## What it shows

Processing an order — validate, total the price, format a receipt, send
it — two ways.

1. **BEFORE (`processOrderTangled`)** — one method does all four things.
   Changing how the receipt looks means editing the same method that
   also validates input and computes totals.
2. **AFTER (`processOrder`)** — four focused pieces (`OrderValidator`,
   `PriceCalculator`, `ReceiptFormatter`, `NotificationService`), each
   owning exactly one concern. `processOrder` just calls them in order.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`registerUserTangled` (bottom of the file, marked `TODO`) mixes three
concerns: validating the username/password, building a welcome message,
and printing it.

**Task:** split it into `UsernameValidator.validate(username, password)`,
`WelcomeMessageBuilder.build(username)`, and a `registerUser(username,
password)` that calls both and prints the result. Update `main` to call
`registerUser`, then delete `registerUserTangled`.

<details>
<summary>Solution</summary>

```java
static class UsernameValidator {
    static void validate(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
    }
}

static class WelcomeMessageBuilder {
    static String build(String username) {
        return "Welcome, " + username + "! Your account is ready.";
    }
}

static void registerUser(String username, String password) {
    UsernameValidator.validate(username, password);
    System.out.println(WelcomeMessageBuilder.build(username));
}

// in main():
registerUser("amina", "s3cur3pw!");
```

</details>
