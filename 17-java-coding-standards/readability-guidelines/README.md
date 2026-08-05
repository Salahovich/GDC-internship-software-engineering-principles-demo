# Readability Guidelines — Naming, Method Size, Magic Numbers

**Files:** `src/codingstandards/readability/`

| Package | File | Role |
|---|---|---|
| `example/` | `NamingUnclear.java` | Naming — BEFORE (`process`) |
| `example/` | `NamingClear.java` | Naming — AFTER (`hasExpired`) |
| `example/` | `MethodSizeMonolithic.java` | Method size — BEFORE (one block) |
| `example/` | `MethodSizeExtracted.java` | Method size — AFTER (named steps) |
| `example/` | `MagicNumberBefore.java` | Magic numbers — BEFORE (`status == 1`) |
| `example/` | `StatusEnumAfter.java` | Magic numbers — AFTER (`Status` enum) |
| `example/` | `ReadabilityGuidelinesDemo.java` | `main()` — runs everything |
| `exercise/` | `NullSafetyTodo.java` | TODO exercise instructions |

## What it shows

Three small, related coding-standard rules, each as a quick BEFORE/AFTER.

1. **Naming** — `process(int d)` vs `hasExpired(int daysSincePayment)`.
   Same logic, but only one of them tells you what it means without a
   comment. A name that needs "and" in it is a sign the method needs
   splitting.
2. **Method size** — `checkoutTotalMonolithic(...)` does everything in
   one block; `checkoutTotal(...)` computes the same total but reads as
   three named steps (`sum`, `applyDiscount`, `calculateTax`). Length
   itself isn't the problem — not being able to hold the whole method in
   your head is.
3. **Magic numbers** — `status == 1` vs `status == Status.CLEARED`. The
   named constant is the documentation; the raw number forces the reader
   to go look it up.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

The other half of "Nulls & Magic Numbers": prefer never producing a null
in the first place (see `exercise/NullSafetyTodo.java` for the given
broken method).

**Task:** write `AdminDirectory.findAdminEmails(...)` that returns
`Collections.emptyList()` instead of null when a team has no admins
configured, so callers can safely for-each the result.

<details>
<summary>Solution</summary>

```java
public class AdminDirectory {
    public static List<String> findAdminEmails(Map<String, List<String>> teamAdmins, String team) {
        List<String> admins = teamAdmins.get(team);
        return admins != null ? admins : Collections.emptyList();
    }
}
```

</details>
