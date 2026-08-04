package kissprinciple.maxofarray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * KISS PRINCIPLE
 * ----------------------------------------------------------------------
 * "Keep It Simple, Stupid": prefer the simplest solution that correctly
 * solves the problem. Extra layers, extra validation, and clever-looking
 * approaches that aren't needed make code harder to read, slower, and
 * more likely to hide bugs — without adding any real value.
 *
 * This demo finds the maximum value in an array of ints two ways:
 * BEFORE, a needlessly complex approach (box into a List, validate the
 * same "not null / not empty" condition four separate times, and sort
 * the ENTIRE array just to read off the last element); AFTER, a plain
 * single-pass loop.
 */
public class KissDemo {

    // ======================================================================
    // 1) BEFORE — violates KISS.
    //    Finding a max is a one-pass problem. Instead this boxes every
    //    element into a List, validates the same condition four times
    //    over, and sorts the whole array (O(n log n)) just to read off
    //    the last element — all to answer something a single loop
    //    answers in O(n).
    // ======================================================================

    static void validateArrayNotNull(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("array must not be null");
    }

    static void validateArrayNotEmpty(int[] arr) {
        if (arr.length == 0) throw new IllegalArgumentException("array must not be empty");
    }

    static void validateListNotNull(List<Integer> list) {
        if (list == null) throw new IllegalArgumentException("list must not be null");
    }

    static void validateListNotEmpty(List<Integer> list) {
        if (list.isEmpty()) throw new IllegalArgumentException("list must not be empty");
    }

    static int getMaxComplex(int[] numbers) {
        validateArrayNotNull(numbers);
        validateArrayNotEmpty(numbers);

        List<Integer> boxed = new ArrayList<>();
        for (int n : numbers) {
            boxed.add(n);
        }
        validateListNotNull(boxed);
        validateListNotEmpty(boxed);

        List<Integer> sorted = boxed.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        validateListNotEmpty(sorted);

        Integer result = sorted.get(sorted.size() - 1);
        return result;
    }

    // ======================================================================
    // 2) AFTER — KISS applied.
    //    One validation, one pass, O(n) — readable in five seconds.
    // ======================================================================

    static int getMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("numbers must not be null or empty");
        }
        int max = numbers[0];
        for (int n : numbers) {
            if (n > max) max = n;
        }
        return max;
    }

    // ======================================================================
    // 3) TODO EXERCISE (~5 minutes)
    //    getMinComplex() below has the exact same problem getMaxComplex()
    //    had — box into a List, validate repeatedly, sort the whole
    //    array — just to read off the FIRST element this time.
    //
    //    Your task: replace it with a plain single-pass loop, the same
    //    way getMax() simplified getMaxComplex(). Update the call in
    //    main() to use your simplified version.
    // ======================================================================

    static int getMinComplex(int[] numbers) {
        validateArrayNotNull(numbers);
        validateArrayNotEmpty(numbers);

        List<Integer> boxed = new ArrayList<>();
        for (int n : numbers) {
            boxed.add(n);
        }
        List<Integer> sorted = boxed.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return sorted.get(0);
    }

    public static void main(String[] args) {
        int[] numbers = {7, 2, 9, 4, 9, 1, 5};

        System.out.println("== BEFORE: sort the whole array just to find the max ==");
        System.out.println("getMaxComplex: " + getMaxComplex(numbers)
                + "  (boxes to a List, validates 4x, sorts O(n log n))");

        System.out.println();
        System.out.println("== AFTER: one pass, one validation, O(n) ==");
        System.out.println("getMax:        " + getMax(numbers));

        System.out.println();
        System.out.println("== TODO exercise: simplify getMinComplex() below ==");
        System.out.println("getMinComplex: " + getMinComplex(numbers)
                + "  (same over-engineering, just for the minimum)");
    }
}
