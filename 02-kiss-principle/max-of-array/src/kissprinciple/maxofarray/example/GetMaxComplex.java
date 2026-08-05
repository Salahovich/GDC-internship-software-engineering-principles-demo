package kissprinciple.maxofarray.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BEFORE: violates KISS. Finding a max is a one-pass problem. Instead
 * this boxes every element into a List, validates the same condition
 * four times over, and sorts the whole array (O(n log n)) just to read
 * off the last element — all to answer something a single loop answers
 * in O(n).
 */
public class GetMaxComplex {

    public static void validateArrayNotNull(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("array must not be null");
    }

    public static void validateArrayNotEmpty(int[] arr) {
        if (arr.length == 0) throw new IllegalArgumentException("array must not be empty");
    }

    public static void validateListNotNull(List<Integer> list) {
        if (list == null) throw new IllegalArgumentException("list must not be null");
    }

    public static void validateListNotEmpty(List<Integer> list) {
        if (list.isEmpty()) throw new IllegalArgumentException("list must not be empty");
    }

    public static int getMaxComplex(int[] numbers) {
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
}
