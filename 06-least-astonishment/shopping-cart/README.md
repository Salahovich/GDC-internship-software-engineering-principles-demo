# Least Astonishment — Shopping Cart

**Files:** `src/leastastonishment/shoppingcart/`

| Package | File | Role |
|---|---|---|
| `example/` | `ShoppingCartSurprising.java` | BEFORE — "getter" mutates the cart |
| `example/` | `ShoppingCart.java` | AFTER — returns copies, cart untouched |
| `example/` | `LeastAstonishmentDemo.java` | `main()` — runs everything |
| `exercise/` | `EmailValidatorSurprising.java` | TODO exercise — given surprising predicate |

## What it shows

A shopping cart's "sorted view" getter, two ways.

1. **BEFORE (`ShoppingCartSurprising.getItemsSortedByPriceSurprising`)**
   — looks like a plain getter, but sorts the cart's own internal list
   in place before returning it. Calling it to display a sorted view
   permanently reorders the actual cart — nothing in the name warns you
   that would happen.
2. **AFTER (`ShoppingCart.getItemsSortedByPrice`)** — returns a new
   sorted copy; the cart's own order is untouched. `getItems()` also
   returns a defensive copy, so callers can't accidentally mutate the
   cart's internals either.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`EmailValidatorSurprising.isValidEmailSurprising` (see
`exercise/EmailValidatorSurprising.java`) is named like a boolean
predicate, but throws a `NullPointerException` when given `null` —
surprising for anyone who expected to write `if
(isValidEmail(input))` without a try/catch.

**Task:** write `isValidEmail(String email)` that returns `false` for
`null` or blank input instead of throwing. Update
`LeastAstonishmentDemo.main` to call it, then delete
`EmailValidatorSurprising`.

<details>
<summary>Solution</summary>

```java
static boolean isValidEmail(String email) {
    if (email == null || email.isBlank()) return false;
    return email.contains("@") && email.contains(".");
}

// in main():
System.out.println(isValidEmail(null)); // false, no exception
```

</details>
