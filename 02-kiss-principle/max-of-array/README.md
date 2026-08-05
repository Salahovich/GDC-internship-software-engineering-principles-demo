# KISS — Get Max of Array

**Files:** `src/kissprinciple/maxofarray/`

| Package | File | Role |
|---|---|---|
| `example/` | `GetMaxComplex.java` | BEFORE — box, validate 4x, sort O(n log n) |
| `example/` | `GetMax.java` | AFTER — one pass, O(n) |
| `example/` | `KissDemo.java` | `main()` — runs everything |
| `exercise/` | `GetMinComplex.java` | TODO exercise — given over-engineered code |

## What it shows

Finding the maximum value in an int array, two ways.

1. **BEFORE (`GetMaxComplex.getMaxComplex`)** — boxes the array into a
   `List<Integer>`, validates "not null / not empty" four separate
   times (once for the array, once for the boxed list, once again after
   sorting...), then sorts the **entire** array (O(n log n)) just to
   read off the last element.
2. **AFTER (`GetMax.getMax`)** — one validation, one pass over the
   array, O(n). Same result, a fraction of the code, easier to trust at
   a glance.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`GetMinComplex.getMinComplex` (see `exercise/GetMinComplex.java`) has
the exact same problem `GetMaxComplex` did — box to a `List`, validate
repeatedly, sort the whole array — just to read off the *first* element
this time.

**Task:** simplify it to a plain single-pass loop, the same way `GetMax`
simplified `GetMaxComplex`, and update the call in `KissDemo.main` to
use your version.

<details>
<summary>Solution</summary>

```java
static int getMin(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
        throw new IllegalArgumentException("numbers must not be null or empty");
    }
    int min = numbers[0];
    for (int n : numbers) {
        if (n < min) min = n;
    }
    return min;
}

// in main():
System.out.println("getMin: " + getMin(numbers));
```

</details>
