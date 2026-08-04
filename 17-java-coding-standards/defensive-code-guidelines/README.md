# Defensive Code Guidelines — Exceptions, Optional, Equals & HashCode

**Files:** `src/codingstandards/defensivecode/`

| Package | File | Role |
|---|---|---|
| `example/` | `ExceptionHandlingExamples.java` | Exceptions — BEFORE/AFTER |
| `example/` | `OptionalExamples.java` | Optional — BEFORE/AFTER |
| `example/` | `EqualsHashCodeExamples.java` | Equals & hashCode — BEFORE/AFTER |
| `example/` | `DefensiveCodeGuidelinesDemo.java` | `main()` — runs everything |
| `exercise/` | `SkuCodeHashCodeTodo.java` | TODO exercise instructions |

## What it shows

Three small, related rules about writing code that fails loudly and
correctly instead of quietly and wrong.

1. **Exceptions** — `parseAmountSwallowed("abc")` catches the error and
   returns `0`, indistinguishable from a real zero amount. `parseAmount("abc")`
   catches the same error and rethrows it wrapped with context — the
   failure is still visible.
2. **Optional** — `findFeeOrNullBefore(...)` returns `null` for "not
   found," and calling `.amount()` on it throws a `NullPointerException`
   with no warning at the call site. `findFeeAfter(...)` returns
   `Optional<Fee>`, so absence is part of the method's signature and
   `.map(...).orElse(...)` handles it explicitly.
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
