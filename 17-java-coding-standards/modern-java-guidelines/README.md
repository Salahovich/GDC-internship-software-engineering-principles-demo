# Modern Java Guidelines — Records, Streams, Logging

**Files:** `src/codingstandards/modernjava/`

| Package | File | Role |
|---|---|---|
| `before/` | `MoneyMutableBefore.java` | Immutability & records — BEFORE (has a setter) |
| `example/` | `MoneyRecordAfter.java` | Immutability & records — AFTER (`record`, no setter) |
| `before/` | `StreamsUnclear.java` | Streams & loops — BEFORE (side effect hidden in `.map()`) |
| `example/` | `StreamsClear.java` | Streams & loops — AFTER (`map` → `filter` → `collect`) |
| `before/` | `LoggingBad.java` | Logging — BEFORE (wrong level, no id, logs a token) |
| `example/` | `LoggingGood.java` | Logging — AFTER (`ERROR`, id included, nothing sensitive) |
| `example/` | `ModernJavaGuidelinesDemo.java` | `main()` — runs everything |
| `exercise/` | `CouponRecordTodo.java` | TODO exercise instructions |

## What it shows

Three small, related rules for writing idiomatic modern Java.

1. **Immutability & records** — `MoneyMutableBefore` has a setter, so a
   method that thinks it's adjusting a local copy actually mutates the
   shared object. `MoneyRecordAfter`, a `record`, gets a constructor,
   accessors, `equals`, `hashCode` and `toString` in one line — and has
   no setter, so that bug class can't exist.
2. **Streams & loops** — `StreamsUnclear.upperCaseLongNamesUnclear(...)`
   hides a side effect inside `.map()` and relies on `.count()` to drive
   the pipeline. It comes back **empty**: `count()` doesn't need to visit
   any elements to answer "how many," so the stream skips `map()`
   entirely and the side effect never runs. `StreamsClear.upperCaseLongNames(...)`
   has no side effect to skip — it's correct because it's just `map` →
   `filter` → `collect`.
3. **Logging** — `LoggingBad.processPaymentBadLogging(...)` logs at the
   wrong level for a real failure, has no identifier to search on, and
   logs a card token. `LoggingGood.processPaymentGoodLogging(...)` logs
   at `ERROR` (someone must act), includes the declaration ID, and logs
   nothing sensitive.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`CouponMutable` is shared across several parts of checkout, and one of
them "temporarily" changes its discount and forgets to change it back
(see `exercise/CouponRecordTodo.java` for the given class).

**Task:** write `Coupon` as a record with `code` and `discountPercent`
components — the same shape as `MoneyRecordAfter` in the example
package. Records have no setters, so this bug can't happen.

<details>
<summary>Solution</summary>

```java
public record Coupon(String code, double discountPercent) {}
```

</details>
