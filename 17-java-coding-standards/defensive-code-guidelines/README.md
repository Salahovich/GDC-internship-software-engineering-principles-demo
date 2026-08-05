# Defensive Code Guidelines — Exceptions, Optional, Equals & HashCode

**Files:** `src/codingstandards/defensivecode/`

| Package | File | Role |
|---|---|---|
| `before/` | `ExceptionSwallowed.java` | Exceptions — BEFORE (swallows and returns `0`) |
| `example/` | `ExceptionWrapped.java` | Exceptions — AFTER (rethrows with context) |
| `example/` | `Fee.java` | Shared entity |
| `before/` | `FeeLookupNullBefore.java` | Optional — BEFORE (returns `null`) |
| `example/` | `FeeLookupOptionalAfter.java` | Optional — AFTER (returns `Optional<Fee>`) |
| `before/` | `OrderIdBroken.java` | Equals & hashCode — BEFORE (`equals` without `hashCode`) |
| `example/` | `OrderId.java` | Equals & hashCode — AFTER (both together) |
| `example/` | `DefensiveCodeGuidelinesDemo.java` | `main()` — runs everything |
| `exercise/` | `SkuCodeHashCodeTodo.java` | TODO exercise instructions |

## What it shows

Three small, related rules about writing code that fails loudly and
correctly instead of quietly and wrong.

1. **Exceptions** — `ExceptionSwallowed.parseAmountSwallowed("abc")`
   catches the error and returns `0`, indistinguishable from a real zero
   amount. `ExceptionWrapped.parseAmount("abc")` catches the same error
   and rethrows it wrapped with context — the failure is still visible.
2. **Optional** — `FeeLookupNullBefore.findFeeOrNullBefore(...)` returns
   `null` for "not found," and calling `.amount()` on it throws a
   `NullPointerException` with no warning at the call site.
   `FeeLookupOptionalAfter.findFeeAfter(...)` returns `Optional<Fee>`, so
   absence is part of the method's signature and `.map(...).orElse(...)`
   handles it explicitly.
3. **Equals & hashCode** — `OrderIdBroken` overrides `equals()` but not
   `hashCode()`. Two "equal" instances land in different `HashSet`
   buckets, so `.contains(...)` returns `false` even though the objects
   are equal. `OrderId` overrides both together, and the lookup works.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`SkuCode` has the same equals/hashCode bug as `OrderIdBroken` (see
`exercise/SkuCodeHashCodeTodo.java` for the given class).

**Task:** add a `hashCode()` override to `SkuCode`, consistent with its
`equals()` — the same shape as `OrderId.hashCode()`.

<details>
<summary>Solution</summary>

```java
public class SkuCode {
    private final String value;

    public SkuCode(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SkuCode other && other.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
```

</details>
